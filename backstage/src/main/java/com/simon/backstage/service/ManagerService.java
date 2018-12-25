package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Manager;
import com.simon.dal.vo.BaseQueryParam;

/**
 * 管理员 Manager 业务层
 * @author fengtianying
 * @date 2018/12/14 13:33
 */
public interface ManagerService {

    /**
     * 新增
     * @param manager
     * @return
     */
    Manager add(Manager manager);

    /**
     * 修改
     * @param manager
     * @return
     */
    int upd(Manager manager);

    /**
     * 删除
     * @param managerId
     * @return
     */
    int del(String managerId);

    /**
     * 列表
     * @param baseQueryParam
     * @return
     */
    PageInfo<Manager> list(BaseQueryParam baseQueryParam);
    
    /**
     * 查询
     * @param record
     * @return
     */
    Manager findManager(Manager record);
    
    /**
     * 根据id查询所拥有的角色名称
     * @param managerId
     * @return
     */
    String findManagerAndRole(String managerId);
}
