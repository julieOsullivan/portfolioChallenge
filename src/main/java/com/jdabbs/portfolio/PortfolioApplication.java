package com.jdabbs.portfolio;

import com.jdabbs.portfolio.enums.InstructionType;
import com.jdabbs.portfolio.implementation.ClientInstructionServiceImpl;
import com.jdabbs.portfolio.implementation.PortfolioServiceImpl;
import com.jdabbs.portfolio.model.ClientInstruction;
import com.jdabbs.portfolio.model.OrderInstruction;
import com.jdabbs.portfolio.model.Portfolio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PortfolioApplication extends SpringBootServletInitializer implements CommandLineRunner {

    private ClientInstructionServiceImpl instructionImpl;

    private ClientInstruction clientInstruction = new ClientInstruction();
    private PortfolioServiceImpl portfolioServiceImpl;

    public PortfolioApplication(ClientInstructionServiceImpl instructionImpl, PortfolioServiceImpl portfolioServiceImpl) {
        this.instructionImpl = instructionImpl;
        this.portfolioServiceImpl = portfolioServiceImpl;
    }

    public static void main(String[] args) {
        SpringApplication.run(PortfolioApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        //find portfolio to take action on http://localhost:8080/portfolio/1
        Portfolio portfolio = portfolioServiceImpl.findById(1L);

        //Mimic user requesting withdrawl http://localhost:8080/ClientInstruction/1/WITHDRAW/10000.00
        clientInstruction.setPortfolio(portfolio);
        clientInstruction.setAction(InstructionType.WITHDRAW.toString());
        clientInstruction.setAmount(10000.00);
        List<OrderInstruction> instructionsToRaise = new ArrayList<>();
        ClientInstruction clientInstructionAdded = instructionImpl.addClientInstruction(clientInstruction);

        // create Instruction  Orders to rebalance portfolio http://localhost:8080/
        instructionsToRaise.addAll(instructionImpl.createPortfolioRebalancingInstructions(1L,20000.00, clientInstructionAdded.getId()));
        // update Client Instruction with new balance
        //display table view with instructions

        System.out.println(instructionsToRaise.toString());

    }


}
