package com.jdabbs.portfolio.service;

import com.jdabbs.portfolio.model.Asset;
import com.jdabbs.portfolio.model.Portfolio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AssetService {

    List<Asset> findAllAssets();

    Asset findAssetById(long id);

    List<Asset> findAssetByPortfolioId(long id);

    void addNewAsset(Asset asset);



}
