package com.jdabbs.portfolio.service;

import com.jdabbs.portfolio.model.ClientInstruction;
import com.jdabbs.portfolio.model.OrderInstruction;

import java.util.List;

public interface OrderInstructionService {

    List<OrderInstruction> getOrderInstructionForClientInstruction(Long portfolioId);

    OrderInstruction addOrderInstruction(OrderInstruction orderInstruction);

    OrderInstruction updateOrderInstruction(OrderInstruction orderInstruction);

    List<OrderInstruction> getAllOrderInstructionOrders();
}

