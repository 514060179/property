package com.simon.backstage.dao;

import com.simon.backstage.domain.model.UnitChargeItem;

public interface UnitChargeItemMapper {
    int deleteByPrimaryKey(String unitItemId);

    int insert(UnitChargeItem record);

    int insertSelective(UnitChargeItem record);

    UnitChargeItem selectByPrimaryKey(String unitItemId);

    int updateByPrimaryKeySelective(UnitChargeItem record);

    int updateByPrimaryKey(UnitChargeItem record);
}