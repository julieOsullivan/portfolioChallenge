package com.jdabbs.portfolio.implementation;

import com.jdabbs.portfolio.model.ClientInstruction;
import com.jdabbs.portfolio.model.OrderInstruction;
import com.jdabbs.portfolio.repository.OrderInstructionRepository;
import com.jdabbs.portfolio.service.OrderInstructionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderInstructionServiceImpl implements OrderInstructionService {

    private OrderInstructionRepository orderRepository;

    public OrderInstructionServiceImpl(OrderInstructionRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<OrderInstruction> getOrderInstructionForClientInstruction(Long clientId) {
        return orderRepository.findOrderInstructionsByMainInstruction_Id(clientId);
    }

    public OrderInstruction addOrderInstruction(OrderInstruction orderInstruction) {
        return orderRepository.save(orderInstruction);
    }

    @Override
    public OrderInstruction updateOrderInstruction(OrderInstruction orderInstruction) {
        Optional<OrderInstruction> orderFromDB = orderRepository.findById(orderInstruction.getId());
        if(orderFromDB.isPresent()) {
            orderFromDB.get().setAsset(orderInstruction.getAsset());
            orderFromDB.get().setOrderType(orderInstruction.getOrderType());
            orderFromDB.get().setOrderAmount(orderInstruction.getOrderAmount());
            orderFromDB.get().setClientInstruction(orderInstruction.getClientInstruction());
        }
        return orderRepository.save(orderInstruction);
    }

    @Override
    public List<OrderInstruction> getAllOrderInstructionOrders() {
        return orderRepository.findAll();
    }
}
