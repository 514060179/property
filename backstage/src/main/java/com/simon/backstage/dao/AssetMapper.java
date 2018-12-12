package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Asset;

public interface AssetMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(Asset record);

    int insertSelective(Asset record);

    Asset selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(Asset record);

    int updateByPrimaryKeyWithBLOBs(Asset record);

    int updateByPrimaryKey(Asset record);
}