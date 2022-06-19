package com.jdabbs.portfolio.implementation;

import com.jdabbs.portfolio.model.Asset;
import com.jdabbs.portfolio.repository.AssetRepository;
import com.jdabbs.portfolio.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


    @Override
    public List<Asset> findAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset findAssetById(long id) {
        return assetRepository.getReferenceById(id);
    }

    @Override
    public List<Asset> findAssetByPortfolioId(long id) {
        return assetRepository.findByPortfolioId(id);
    }

    @Override
    public void addNewAsset(Asset asset) {
        assetRepository.save(asset);
    }

    public Double getAssetPrice(String assetId) {
        Map<String, Double> assetPrices = new HashMap<>();
        assetPrices.put ("IE00B52L4369", 100.00);
        assetPrices.put ("GB00BQ1YHQ70", 100.00);
        assetPrices.put("GB00B3X7QG63", 100.00);
        assetPrices.put("GB00BG0QP828", 100.00);
        assetPrices.put("GB00BPN5P238", 100.00);
        assetPrices.put("IE00B1S74Q32", 100.00);

        return assetPrices.get(assetId);
    }
}

