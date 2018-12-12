package com.simon.backstage.dao;

import com.simon.backstage.domain.model.UserUnit;

public interface UserUnitMapper {
    int deleteByPrimaryKey(Long userUnitId);

    int insert(UserUnit record);

    int insertSelective(UserUnit record);

    UserUnit selectByPrimaryKey(Long userUnitId);

    int updateByPrimaryKeySelective(UserUnit record);

    int updateByPrimaryKey(UserUnit record);
}