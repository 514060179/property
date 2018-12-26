package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.ManagerMapper;
import com.simon.backstage.domain.model.Manager;
import com.simon.backstage.service.ManagerService;
import com.simon.dal.util.EncryUtil;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fengtianying
 * @date 2018/12/14 13:33
 */
@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public Manager add(Manager manager) {
        manager.setManagerId(UUIDUtil.uidString());
        manager.setPassword(EncryUtil.getMD5(manager.getPassword()));
        managerMapper.insertSelective(manager);//默认普通管理员角色
        //添加角色关系
        return manager;
    }

    @Override
    public int upd(Manager manager) {
        return managerMapper.updateByPrimaryKeySelective(manager);
    }

    @Override
    public int del(String managerId) {
        return managerMapper.deleteByPrimaryKey(managerId);
    }

    @Override
    public PageInfo<Manager> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(managerMapper.selectByCondition(baseQueryParam));
    }

	@Override
	public Manager findManager(Manager record) {
		// TODO Auto-generated method stub
		return managerMapper.findManager(record);
	}

	@Override
	public String findManagerAndRole(String managerId) {
		// TODO Auto-generated method stub
		return managerMapper.findManagerAndRole(managerId);
	}
}
