package com.jdabbs.portfolio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * OrderInstructions are created as a result of a client instruction. They are created to
 * keep the assets in the portfolio at their given percentage. So, in the case of a withdrawl
 * they will produce sell instructions for each asset based on an arbitraty price which I
 * use to populate a hashmap on start up. This could be replaced by an api look up to relevant stock exchange
 */

@Entity
public class OrderInstruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String asset;
    private String orderType;
    private Double orderAmount;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JsonIgnore
    private ClientInstruction mainInstruction;

    public OrderInstruction() {
    }

    public OrderInstruction(String asset, String orderType, Double orderAmount, ClientInstruction clientInstructions) {
        this.asset = asset;
        this.orderType = orderType;
        this.orderAmount = orderAmount;
        this.mainInstruction = clientInstructions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    @JsonIgnore
    public ClientInstruction getClientInstruction() {
        return mainInstruction;
    }

    public void setClientInstruction(ClientInstruction clientInstruction) {
        this.mainInstruction = clientInstruction;
    }

//    @Override
//    public String toString() {
//        return "OrderInstruction{" +
//                "id=" + id +
//                ", asset='" + asset + '\'' +
//                ", orderType=" + orderType +
//                ", orderAmount=" + orderAmount +
//                ", mainInstruction=" + mainInstruction +
//                '}';
//    }
}
