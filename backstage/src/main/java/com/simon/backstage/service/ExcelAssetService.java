package com.simon.backstage.service;

import com.simon.backstage.domain.model.Asset;
import com.simon.backstage.domain.vo.AssetQueryParam;

import java.util.List;

public interface ExcelAssetService {
    List<Asset> execlAsset(AssetQueryParam assetQueryParam);
}
