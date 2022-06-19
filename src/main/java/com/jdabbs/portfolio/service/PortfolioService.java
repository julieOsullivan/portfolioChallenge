package com.jdabbs.portfolio.service;


import com.jdabbs.portfolio.model.Portfolio;

import java.util.List;

public interface PortfolioService {

    Portfolio findById(Long l);

    List<Portfolio> findAll();

    Portfolio addNewPortfolio(Portfolio portfolio);

    Portfolio updatePortfolio(Portfolio portfolio);
}
