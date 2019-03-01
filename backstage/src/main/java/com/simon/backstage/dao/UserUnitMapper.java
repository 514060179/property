package com.simon.backstage.dao;

import org.apache.ibatis.annotations.Param;

import com.simon.backstage.domain.model.UserUnit;

public interface UserUnitMapper {
    int deleteByPrimaryKey(Long userUnitId);

    int insert(UserUnit record);

    int insertSelective(UserUnit record);

    UserUnit selectByPrimaryKey(Long userUnitId);

    UserUnit selectByUnitId(String unitId);

    int updateByPrimaryKeySelective(UserUnit record);

    int updateByPrimaryKey(UserUnit record);

	int deleteByUser(@Param("unitId") String unitId,@Param("userId") String userId);
}