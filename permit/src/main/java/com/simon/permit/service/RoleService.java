package com.simon.permit.service;

import com.github.pagehelper.PageInfo;
import com.simon.permit.model.BaseQueryParam;
import com.simon.permit.model.Role;
import com.simon.permit.model.RoleJn;

import java.util.List;

/**
 * @author fengtianying
 * @date 2018/12/20 16:06
 */
public interface RoleService {

    /**
     * 获取列表
     * @param baseQueryParam
     * @return
     */
    PageInfo<Role> list(BaseQueryParam baseQueryParam);

    /**
     * 添加角色
     * @param role
     * @return
     */
    Role add(Role role);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    int roleDel(Long roleId);

    /**
     * 根据id查询角色
     * @param roleId
     * @return
     */
    Role findRoleById(Long roleId);

    /**
     * 根据角色id获取权限列表
     * @param roleId
     * @return
     */
    List<RoleJn> listByRoleId(Long roleId);

    /**
     * 根据角色id获取权限列表(没有选择的权限)
     * @param roleId
     * @return
     */
    List<RoleJn> noChoiceListByRoleId(Long roleId);

    /**
     * 删除权限
     * @param roleJnId
     * @return
     */
    int roleJnDel(Long roleJnId);

    /**
     * 添加权限
     * @param roleJn
     * @return
     */
    RoleJn roleJnAdd(RoleJn roleJn);
}
