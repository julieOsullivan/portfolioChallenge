package com.jdabbs.portfolio.repository;


import com.jdabbs.portfolio.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByPortfolioId(Long portfolioId);

    void deleteByPortfolioId(long portfolioId);
}
