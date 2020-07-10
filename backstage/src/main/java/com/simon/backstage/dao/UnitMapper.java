package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Unit;
import com.simon.dal.vo.BaseQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UnitMapper {
    int deleteByPrimaryKey(String unitId);

    int insert(Unit record);

    int insertSelective(Unit record);

    Unit selectByPrimaryKey(String unitId);

    Unit selectByUnitNo(@Param("unitNo") String unitNo, @Param("communityId")String communityId);

    List<Unit> selectByCondition(BaseQueryParam baseQueryParam);

    List<Unit> getUnitByUserId(String userId);

    int updateByPrimaryKeySelective(Unit record);

    int updateByPrimaryKey(Unit record);


}