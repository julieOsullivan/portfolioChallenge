package com.jdabbs.portfolio.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * The portfolio is the main parent and container to the Account. Listing the overall balance
 * and list of assets within
 */

@Entity
@Table(name="portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="portfolioBalance")
    private Double portfolioBalance;

    @OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY)
    private List<Asset> assets;

    @OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY)
    private List<ClientInstruction> clientInstructions;

    public Portfolio() {}

    public Portfolio(Double portfolioBalance,List<Asset> assets) {
        this.portfolioBalance = portfolioBalance;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPortfolioBalance() {
        return portfolioBalance;
    }

    public void setPortfolioBalance(Double portfolioBalance) {
        this.portfolioBalance = portfolioBalance;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<ClientInstruction> getClientInstructions() {
        return clientInstructions;
    }

    public void setClientInstructions(List<ClientInstruction> clientInstructions) {
        this.clientInstructions = clientInstructions;
    }
}
