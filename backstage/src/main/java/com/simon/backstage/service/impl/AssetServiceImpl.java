package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.AssetMapper;
import com.simon.backstage.domain.model.Asset;
import com.simon.backstage.service.AssetService;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 资产业务层
 */
@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetMapper assetMapper;
    @Override
    public Asset add(Asset asset) {
        asset.setAssetId(UUIDUtil.uidString());
        assetMapper.insertSelective(asset);
        return asset;
    }

    @Override
    public Asset detail(String assetId) {
        return assetMapper.selectByPrimaryKey(assetId);
    }

    @Override
    public int upd(Asset asset) {
        return assetMapper.updateByPrimaryKeySelective(asset);
    }

    @Override
    public int del(String assetId) {
        return assetMapper.deleteByPrimaryKey(assetId);
    }

    @Override
    public PageInfo<Asset> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(assetMapper.selectByCondition(baseQueryParam));
    }
}
