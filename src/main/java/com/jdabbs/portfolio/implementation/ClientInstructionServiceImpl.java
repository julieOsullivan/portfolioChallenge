package com.jdabbs.portfolio.implementation;


import com.jdabbs.portfolio.model.Asset;
import com.jdabbs.portfolio.model.ClientInstruction;
import com.jdabbs.portfolio.model.OrderInstruction;
import com.jdabbs.portfolio.model.Portfolio;
import com.jdabbs.portfolio.repository.ClientInstructionRepository;
import com.jdabbs.portfolio.service.ClientInstructionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ClientInstructionServiceImpl implements ClientInstructionService {

    private ClientInstructionRepository clientInstructionRepository;
    private PortfolioServiceImpl portFolioServiceImpl;
    private AssetServiceImpl assetService;
    private OrderInstructionServiceImpl orderServiceImpl;

    public ClientInstructionServiceImpl(ClientInstructionRepository clientInstructionRepository, PortfolioServiceImpl portFolioServiceImpl,
                                        AssetServiceImpl assetService, OrderInstructionServiceImpl orderServiceImpl) {

        this.clientInstructionRepository = clientInstructionRepository;
        this.portFolioServiceImpl = portFolioServiceImpl;
        this.assetService = assetService;
        this.orderServiceImpl = orderServiceImpl;
    }

    @Override
    public List<OrderInstruction> createPortfolioRebalancingInstructions(Long portfolioId, Double amount, Long clientInstruction) {

        Optional clientInstructionCheck = clientInstructionRepository.findById(clientInstruction);
        ClientInstruction clientInstructionAdded = new ClientInstruction();
        if(clientInstructionCheck.isPresent()) clientInstructionAdded = (ClientInstruction) clientInstructionCheck.get();

        //get the portfolio to be updated and store both current balance and new balance
        Portfolio portfolio = portFolioServiceImpl.findById(portfolioId);

        //calculate the current and new portfolio main balance after making withdrawl
        Double currentPortFolioBalance = portfolio.getPortfolioBalance();
        Double newPortFolioBalance = currentPortFolioBalance - amount;

        //get list of all assets relating to this portfolio
        List<Asset> portfolioAssets = assetService.findAssetByPortfolioId(portfolioId);
        List<OrderInstruction> newInstructions = new ArrayList<>();
        // for each asset  calculate initial value and new reduced value to keep asset at same percentage
        for (Asset asset: portfolioAssets) {
            OrderInstruction orderInstruction = new OrderInstruction();

            // calculate the number to sell to meet this amount and keep asset at same percentage of new balance
            Double currentRealShareValue = (asset.getAssetPortfolioPercentage() * currentPortFolioBalance)/100;
            Double newRealShareValue = (asset.getAssetPortfolioPercentage() * newPortFolioBalance)/100;
            Double adjustmentAmount = currentRealShareValue - newRealShareValue;
            Double sharesToSell = adjustmentAmount/ (assetService.getAssetPrice(asset.getIsin()));

            //populate new orderInstruction and add to main Instruction parent
            orderInstruction.setOrderAmount(sharesToSell);
            orderInstruction.setOrderType("SELL");
            orderInstruction.setAsset(asset.getIsin());
            orderInstruction.setClientInstruction(clientInstructionAdded);
            orderServiceImpl.addOrderInstruction(orderInstruction);
            // save the order instruction to the order instruction list
            newInstructions.add(orderInstruction);
        }
        clientInstructionAdded.setOrderInstructions(newInstructions);
        ClientInstruction updatedClient = updateClientInstruction(clientInstructionAdded);

        return newInstructions;
    }


    @Override
    public ClientInstruction addClientInstruction(ClientInstruction clientInstruction) {
        return clientInstructionRepository.save(clientInstruction);
    }

    @Override
    public ClientInstruction updateClientInstruction(ClientInstruction clientInstruction) {
        Optional<ClientInstruction> clientFromDB = clientInstructionRepository.findById(clientInstruction.getId());
        if(clientFromDB.isPresent()) {
            clientFromDB.get().setOrderInstructions(clientInstruction.getOrderInstructions());
            clientFromDB.get().setAmount(clientInstruction.getAmount());
            clientFromDB.get().setAction(clientInstruction.getAction());
        }
        return clientInstructionRepository.save(clientInstruction);
    }

    @Override
    public void deleteClientInstruction(Long id) {
        clientInstructionRepository.deleteById(id);
    }


    public List<ClientInstruction> getAllClientInstructionOrders() {
        return clientInstructionRepository.findAll();
    }

}
