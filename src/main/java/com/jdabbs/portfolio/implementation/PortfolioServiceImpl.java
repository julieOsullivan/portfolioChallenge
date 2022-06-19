package com.jdabbs.portfolio.implementation;

import com.jdabbs.portfolio.model.Portfolio;
import com.jdabbs.portfolio.repository.PortfolioRepository;
import com.jdabbs.portfolio.service.PortfolioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public Portfolio findById(Long id) {
        Optional<Portfolio> portfolioOptional = portfolioRepository.findById(id);
        if (portfolioOptional.isEmpty()) {
            return null;
        }
        return portfolioOptional.get();
    }

    @Override
    public List<Portfolio> findAll() {
        return portfolioRepository.findAll();
    }


    @Override
    public Portfolio addNewPortfolio(Portfolio portfolio) {
        portfolioRepository.save(portfolio);
        return portfolio;
    }

    @Override
    public Portfolio updatePortfolio(Portfolio portfolio) {
        Optional<Portfolio> portfolioFromDB = portfolioRepository.findById(portfolio.getId());
        if(portfolioFromDB.isPresent()) {
            portfolioFromDB.get().setPortfolioBalance(portfolioFromDB.get().getPortfolioBalance());
            portfolioFromDB.get().setAssets(portfolioFromDB.get().getAssets());
            portfolioFromDB.get().setClientInstructions(portfolioFromDB.get().getClientInstructions());
        }
        return portfolioRepository.save(portfolio);
    }
}
