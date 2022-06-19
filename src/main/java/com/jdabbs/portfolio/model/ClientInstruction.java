package com.jdabbs.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.List;

/**
 * This entity is the main container to hold the clients instruction to update the balance
 * of a portfolio
 */

@Entity
public class ClientInstruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "portfolio_id", nullable = false)
    @JsonIgnore
    private Portfolio portfolio;

    private String action;
    private Double Amount;

    @OneToMany(mappedBy = "mainInstruction", fetch = FetchType.LAZY)
    private List<OrderInstruction> orderInstruction;

    public ClientInstruction() {
    }


    public ClientInstruction(Portfolio portfolio, String action, Double amount, List<OrderInstruction> orderInstruction) {
        this.portfolio = portfolio;
        this.action = action;
        this.Amount = amount;
        this.orderInstruction = orderInstruction;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public List<OrderInstruction> getOrderInstructions() {
        return orderInstruction;
    }

    public void setOrderInstructions(List<OrderInstruction> orderInstruction) {
        this.orderInstruction = orderInstruction;
    }

    @Override
    public String toString() {
        return "ClientInstruction{" +
                "id=" + id +
                ", portfolio=" + portfolio +
                ", action='" + action + '\'' +
                ", Amount=" + Amount +
                ", orderInstruction=" + orderInstruction +
                '}';
    }
}
