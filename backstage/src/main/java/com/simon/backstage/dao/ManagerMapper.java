package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Jurisdiction;
import com.simon.backstage.domain.model.Manager;
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
}