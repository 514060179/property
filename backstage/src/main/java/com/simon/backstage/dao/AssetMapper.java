package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Asset;
import com.simon.dal.vo.BaseClaims;

import java.util.List;

public interface AssetMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(Asset record);

    int insertSelective(Asset record);

    Asset selectByPrimaryKey(String assetId);

    List<Asset> selectByCondition(BaseClaims BaseClaims);

    int updateByPrimaryKeySelective(Asset record);

    int updateByPrimaryKeyWithBLOBs(Asset record);

    int updateByPrimaryKey(Asset record);
}