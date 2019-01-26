package com.simon.backstage.dao;

import com.simon.backstage.domain.model.AdvanceMoney;

public interface AdvanceMoneyMapper {
    int deleteByPrimaryKey(String advanceId);

    int insert(AdvanceMoney record);

    int insertSelective(AdvanceMoney record);

    AdvanceMoney selectByPrimaryKey(String advanceId);

    AdvanceMoney findByUserId(String userId);

    int updateByPrimaryKeySelective(AdvanceMoney record);

    int updateByPrimaryKey(AdvanceMoney record);
}