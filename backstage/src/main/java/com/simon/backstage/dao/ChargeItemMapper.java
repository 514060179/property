package com.simon.backstage.dao;

import com.simon.backstage.domain.model.ChargeItem;

public interface ChargeItemMapper {
    int deleteByPrimaryKey(String itemId);

    int insert(ChargeItem record);

    int insertSelective(ChargeItem record);

    ChargeItem selectByPrimaryKey(String itemId);

    int updateByPrimaryKeySelective(ChargeItem record);

    int updateByPrimaryKey(ChargeItem record);
}