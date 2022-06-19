package com.jdabbs.portfolio.controller;


import com.jdabbs.portfolio.implementation.ClientInstructionServiceImpl;
import com.jdabbs.portfolio.implementation.OrderInstructionServiceImpl;
import com.jdabbs.portfolio.implementation.PortfolioServiceImpl;
import com.jdabbs.portfolio.model.ClientInstruction;
import com.jdabbs.portfolio.model.OrderInstruction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/OrderInstruction")
public class OrderInstructionController {

    private ClientInstructionServiceImpl clientInstructionServiceImpl;
    private PortfolioServiceImpl portfolioServiceImpl;
    private OrderInstructionServiceImpl orderInstructionServiceImpl;

    public OrderInstructionController(ClientInstructionServiceImpl clientInstructionServiceImpl, PortfolioServiceImpl portfolioServiceImpl, OrderInstructionServiceImpl orderInstructionServiceImpl) {
        this.clientInstructionServiceImpl = clientInstructionServiceImpl;
        this.portfolioServiceImpl = portfolioServiceImpl;
        this.orderInstructionServiceImpl = orderInstructionServiceImpl;
    }

    @GetMapping(path = "/{id}")
    public List<OrderInstruction> getOrderInstructionForClientInstruction(@PathVariable Long id) {
        return orderInstructionServiceImpl.getOrderInstructionForClientInstruction(id);    }

    @GetMapping(path ="/ALL/")
    public List<OrderInstruction> getAllClientInstructionOrders() {
        return orderInstructionServiceImpl.getAllOrderInstructionOrders();
    }

    @GetMapping(path="/")
    public List<OrderInstruction> getNewOrderInstructionsfor(@PathVariable Long portfolioId, @PathVariable Double amount,
                                                             @PathVariable Long clientInstructionAdded) {
        return clientInstructionServiceImpl.createPortfolioRebalancingInstructions(portfolioId, amount, clientInstructionAdded);
    }

    @PostMapping(path="/")
    public OrderInstruction addOrderInstruction(@RequestBody OrderInstruction orderInstruction){
        return orderInstructionServiceImpl.addOrderInstruction(orderInstruction);
    }

    @PutMapping(path="/")
    public OrderInstruction updateOrderInstruction(@RequestBody OrderInstruction orderInstruction){
        return orderInstructionServiceImpl.addOrderInstruction(orderInstruction);
    }
}
