package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Advertisement;

public interface AdvertisementMapper {
    int deleteByPrimaryKey(String advId);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    Advertisement selectByPrimaryKey(String advId);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
}