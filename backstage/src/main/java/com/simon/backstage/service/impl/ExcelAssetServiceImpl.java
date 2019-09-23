package com.simon.backstage.service.impl;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.AssetMapper;
import com.simon.backstage.domain.model.Asset;
import com.simon.backstage.domain.vo.AssetQueryParam;
import com.simon.backstage.service.ExcelAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelAssetServiceImpl implements ExcelAssetService {

    @Autowired
    private AssetMapper assetMapper;

    @Override
    public List<Asset> execlAsset(AssetQueryParam assetQueryParam) {
        return assetMapper.selectByCondition(assetQueryParam);
    }
}
