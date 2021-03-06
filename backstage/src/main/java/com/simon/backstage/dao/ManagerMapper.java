package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Jurisdiction;
import com.simon.backstage.domain.model.Manager;
import com.simon.backstage.domain.model.Role;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;
import java.util.Map;

public interface ManagerMapper {
    int deleteByPrimaryKey(String managerId);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(String managerId);

    List<Manager> selectByCondition(BaseQueryParam baseQueryParam);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    List<Map<String,String>> findCustomRolesAuthorization();

    List<String> findAllJurisdiction();

    int addJurisdiction(List<Jurisdiction> list);

    int addAdminRoleJn(List<Map<String,Long>> list);

    int initRole();

    Long findAdminRole(String roleName);

    Manager findManager(Manager record);
    
    String findManagerAndRole(String managerId);

    String findManagerAndRoleIds(String managerId);

    int addUserRole(List<Map<String,Object>> list);

    List<Role> findAllRole();
}