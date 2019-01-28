package com.simon.backstage.dao;

import com.simon.backstage.domain.model.ChargeItem;
import com.simon.backstage.domain.vo.UnitWithItem;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;

public interface ChargeItemMapper {
    int deleteByPrimaryKey(String itemId);

    int insert(ChargeItem record);

    int insertSelective(ChargeItem record);

    ChargeItem selectByPrimaryKey(String itemId);

    List<ChargeItem> selectByCondition(BaseQueryParam baseQueryParam);

    int updateByPrimaryKeySelective(ChargeItem record);

    int updateByPrimaryKey(ChargeItem record);

    int unitAddItem(List<UnitWithItem> list);

    List<ChargeItem> unitItemList(BaseQueryParam baseQueryParam);
}