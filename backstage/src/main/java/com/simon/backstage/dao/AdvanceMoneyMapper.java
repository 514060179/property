package com.simon.backstage.dao;

import com.simon.backstage.domain.model.AdvanceMoney;

import java.util.List;

public interface AdvanceMoneyMapper {
    int deleteByPrimaryKey(String advanceId);

    int insert(AdvanceMoney record);

    int insertSelective(AdvanceMoney record);

    AdvanceMoney selectByPrimaryKey(String advanceId);

    AdvanceMoney findByUserId(String userId);

    int updateByPrimaryKeySelective(AdvanceMoney record);

    int updateByPrimaryKey(AdvanceMoney record);


    /**
     * 获取所有账号
     * @return
     */
    List<AdvanceMoney> allAdvanceMoney();

    /**
     * 更新
     * @param advanceMoney
     * @return
     */
    int updateByUserId(AdvanceMoney advanceMoney);
}