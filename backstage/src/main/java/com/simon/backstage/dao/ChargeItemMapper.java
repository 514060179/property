package com.simon.backstage.dao;

import com.simon.backstage.domain.model.ChargeItem;
import com.simon.backstage.domain.vo.UnitItemWithUser;
import com.simon.backstage.domain.vo.UnitWithItem;
import com.simon.dal.vo.BaseQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChargeItemMapper {
    int deleteByPrimaryKey(String itemId);

    int insert(ChargeItem record);

    int insertSelective(ChargeItem record);

    ChargeItem selectByPrimaryKey(String itemId);

    ChargeItem selectByItemIdAndUnitId(@Param("itemId") String itemId, @Param("unitId") String unitId);

    List<ChargeItem> selectByCondition(BaseQueryParam baseQueryParam);

    int updateByPrimaryKeySelective(ChargeItem record);

    int updateAllTemporary();

    int updateByPrimaryKey(ChargeItem record);

    int unitAddItem(List<UnitWithItem> list);

    List<ChargeItem> unitItemList(BaseQueryParam baseQueryParam);

    List<UnitItemWithUser> cycleUnitItem();
}