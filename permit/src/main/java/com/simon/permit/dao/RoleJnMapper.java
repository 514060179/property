package com.simon.permit.dao;


import com.simon.permit.model.RoleJn;

public interface RoleJnMapper {
    int deleteByPrimaryKey(Long roleJnId);

    int insert(RoleJn record);

    int insertSelective(RoleJn record);

    RoleJn selectByPrimaryKey(Long roleJnId);

    int updateByPrimaryKeySelective(RoleJn record);

    int updateByPrimaryKey(RoleJn record);
}