package com.jdabbs.portfolio.service;

import com.jdabbs.portfolio.model.ClientInstruction;
import com.jdabbs.portfolio.model.OrderInstruction;

import java.util.List;

public interface ClientInstructionService {

    List<OrderInstruction> createPortfolioRebalancingInstructions(Long portfolioId, Double amount, Long clientInstructionAdded);

    ClientInstruction addClientInstruction(ClientInstruction clientInstruction);

    void deleteClientInstruction(Long id);

    ClientInstruction updateClientInstruction(ClientInstruction clientInstruction);

}
