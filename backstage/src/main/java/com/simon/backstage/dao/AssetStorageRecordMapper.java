package com.simon.backstage.dao;

import com.simon.backstage.domain.model.AssetStorageRecord;

public interface AssetStorageRecordMapper {
    int deleteByPrimaryKey(String storageRecordId);

    int insert(AssetStorageRecord record);

    int insertSelective(AssetStorageRecord record);

    AssetStorageRecord selectByPrimaryKey(String storageRecordId);

    int updateByPrimaryKeySelective(AssetStorageRecord record);

    int updateByPrimaryKey(AssetStorageRecord record);
}