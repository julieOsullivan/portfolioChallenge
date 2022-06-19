package com.jdabbs.portfolio.controller;


import com.jdabbs.portfolio.enums.InstructionType;
import com.jdabbs.portfolio.implementation.ClientInstructionServiceImpl;
import com.jdabbs.portfolio.implementation.OrderInstructionServiceImpl;
import com.jdabbs.portfolio.implementation.PortfolioServiceImpl;
import com.jdabbs.portfolio.model.ClientInstruction;
import com.jdabbs.portfolio.model.OrderInstruction;
import com.jdabbs.portfolio.model.Portfolio;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/ClientInstruction")
public class ClientInstructionController {


    private ClientInstructionServiceImpl clientInstructionServiceImpl;
    private PortfolioServiceImpl portfolioServiceImpl;
    private OrderInstructionServiceImpl orderInstructionServiceImpl;

    public ClientInstructionController(ClientInstructionServiceImpl clientInstructionServiceImpl, PortfolioServiceImpl portfolioServiceImpl,
                                       OrderInstructionServiceImpl orderInstructionServiceImpl) {
        this.clientInstructionServiceImpl = clientInstructionServiceImpl;
        this.portfolioServiceImpl = portfolioServiceImpl;
        this.orderInstructionServiceImpl = orderInstructionServiceImpl;
    }
    @GetMapping(path ="/")
    public List<ClientInstruction> getAllClientInstructionOrders() {
        return clientInstructionServiceImpl.getAllClientInstructionOrders();
    }

    @GetMapping(path = "order/{id}/{action}/{amount}")
    public List<OrderInstruction> getInstructionOrder(@PathVariable Long id,@PathVariable String action, @PathVariable Double amount){
        Portfolio portfolio = portfolioServiceImpl.findById(id);
        ClientInstruction clientInstruction = new ClientInstruction();
        clientInstruction.setPortfolio(portfolio);
        clientInstruction.setAction(InstructionType.WITHDRAW.toString());
        clientInstruction.setAmount(amount);
        List<OrderInstruction> instructionsToRaise = new ArrayList<>();
        clientInstruction.setOrderInstructions(instructionsToRaise);
        ClientInstruction clientInstructionAdded = clientInstructionServiceImpl.addClientInstruction(clientInstruction);
        return clientInstructionServiceImpl.createPortfolioRebalancingInstructions(id,clientInstruction.getAmount(),clientInstructionAdded.getId());
    }

    @PutMapping(path="/")
    public ClientInstruction updateClientInstruction(@RequestBody ClientInstruction clientInstruction){
        return clientInstructionServiceImpl.addClientInstruction(clientInstruction);
    }

    @DeleteMapping(path="/{id}")
    public void deleteClientInstruction(@PathVariable Long id){
        clientInstructionServiceImpl.deleteClientInstruction(id);
    }

}
