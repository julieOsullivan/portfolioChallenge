package com.jdabbs.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

/**
 * This entity basically lists the current assets and their
 * percentage weighting to the main portfolio balance
 */

@Entity
@Table(name = "asset")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isin;
    private String assetDescription;
    private int assetPortfolioPercentage;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="portfolio_id", nullable = false)
    @JsonIgnore
    private Portfolio portfolio;

    public Asset() {
    }

    public Asset(String isin, String assetDescription, int assetPortfolioPercentage,Portfolio portfolio) {
        this.isin = isin;
        this.assetDescription = assetDescription;
        this.assetPortfolioPercentage = assetPortfolioPercentage;
        this.portfolio = portfolio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public int getAssetPortfolioPercentage() {
        return assetPortfolioPercentage;
    }

    public void setAssetPortfolioPercentage(int assetPortfolioPercentage) {
        this.assetPortfolioPercentage = assetPortfolioPercentage;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
