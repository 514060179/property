package com.simon.backstage.service;

import com.simon.backstage.dao.ManagerMapper;
import com.simon.backstage.domain.model.Jurisdiction;
import com.simon.backstage.domain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
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

    public List<Role> findAllRole(){
        return managerMapper.findAllRole();
    }
    public List<String> findAllJurisdiction(){
        return managerMapper.findAllJurisdiction();
    }

    public int addJurisdiction(List<Jurisdiction> jurisdictionList,Long roleId){
        //初始化admin权限
        int i = managerMapper.addJurisdiction(jurisdictionList);
        List<Map<String,Long>> mapList = new ArrayList<>();
        if (i>0){
            jurisdictionList.forEach(jurisdiction -> {
                Map<String,Long> map = new HashMap<>();
                map.put("roleId",roleId);
                map.put("jnId",jurisdiction.getJnId());
                mapList.add(map);
            });
            //添加admin权限
            managerMapper.addAdminRoleJn(mapList);
        }
        return i;
    }

    public int initRole(){
        return managerMapper.initRole();
    }

    public Long findRoleByName(String roleName){
        return managerMapper.findAdminRole(roleName);
    }
}
