package com.simon.backstage.service;

import com.simon.backstage.dao.ManagerMapper;
import com.simon.backstage.domain.model.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 权限业务层
 * @author fengtianying
 * @date 2018/12/20 10:38
 */
public class RoleService {

    @Autowired
    private ManagerMapper managerMapper;

    /**
     * 获取所有角色的权限
     * @return
     */
    public List<Map<String,String>> findCustomRolesAuthorization(){
        return managerMapper.findCustomRolesAuthorization();
    }

    public List<String> findAllJurisdiction(){
        return managerMapper.findAllJurisdiction();
    }

    public int addJurisdiction(List<Jurisdiction> jurisdictionList){
        return managerMapper.addJurisdiction(jurisdictionList);
    }

    public int initRole(){
        return managerMapper.initRole();
    }

    public Long findAdminRole(){
        return managerMapper.findAdminRole();
    }
}
