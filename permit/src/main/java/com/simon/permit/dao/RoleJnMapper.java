package com.simon.permit.dao;


import com.simon.permit.model.RoleJn;

import java.util.List;

public interface RoleJnMapper {
    int deleteByPrimaryKey(Long roleJnId);

    int insert(RoleJn record);

    int insertSelective(RoleJn record);

    RoleJn selectByPrimaryKey(Long roleJnId);

    List<RoleJn> selectByRoleId(Long roleId);

    List<RoleJn> noSelectByRoleId(Long roleId);

    int updateByPrimaryKeySelective(RoleJn record);

    int updateByPrimaryKey(RoleJn record);
}