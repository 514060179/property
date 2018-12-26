package com.simon.permit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.permit.dao.RoleJnMapper;
import com.simon.permit.dao.RoleMapper;
import com.simon.permit.model.BaseQueryParam;
import com.simon.permit.model.Role;
import com.simon.permit.model.RoleJn;
import com.simon.permit.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

/**
 * @author fengtianying
 * @date 2018/12/20 16:11
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleJnMapper roleJnMapper;
    @Override
    public PageInfo<Role> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(roleMapper.findByCondition(baseQueryParam));
    }

    @Override
    public Role add(Role role) {
        int i = 0;
        if (Objects.isNull(role.getRoleId())) {
            i = roleMapper.insertSelective(role);
        } else {
            i = roleMapper.updateByPrimaryKeySelective(role);
        }
        if (i > 0) {
            return role;
        } else {
            return null;
        }
    }

    @Override
    public Role findRoleById(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<RoleJn> listByRoleId(Long roleId) {
        return roleJnMapper.selectByRoleId(roleId);
    }

    @Override
    public List<RoleJn> noChoiceListByRoleId(Long roleId) {
        return roleJnMapper.selectByRoleId(roleId);
    }

    @Override
    public int roleJnDel(Long roleJnId) {
        return roleJnMapper.deleteByPrimaryKey(roleJnId);
    }

    @Override
    public RoleJn roleJnAdd(RoleJn roleJn) {
        if (roleJnMapper.insertSelective(roleJn)>0){
            return roleJn;
        }else{
            return null;
        }
    }
}
