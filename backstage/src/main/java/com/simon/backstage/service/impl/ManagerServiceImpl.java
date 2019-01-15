package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.ManagerMapper;
import com.simon.backstage.domain.model.Manager;
import com.simon.backstage.service.ManagerService;
import com.simon.dal.dao.CommunityMapper;
import com.simon.dal.util.EncryUtil;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fengtianying
 * @date 2018/12/14 13:33
 */
@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private CommunityMapper communityMapper;

    @Transactional
    @Override
    public Manager add(Manager manager,String[] roleIds) {
        manager.setManagerId(UUIDUtil.uidString());
        manager.setPassword(EncryUtil.getMD5(manager.getPassword()));
        managerMapper.insertSelective(manager);//默认普通管理员角色
        //添加角色关系
        List<Map<String,Object>> list = new ArrayList<>();
        for (String roleId:roleIds
             ) {
            Map<String,Object> map = new HashMap<>();
            map.put("roleId",roleId);
            map.put("userId",manager.getManagerId());
            list.add(map);
        }
        managerMapper.addUserRole(list);
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
        List<Manager> result = managerMapper.selectByCondition(baseQueryParam);
        List<Manager> list = new ArrayList<Manager>();
        for (Manager manager : result) {
        	String communityName = communityMapper.findName(manager.getCommunityId());
        	manager.setCommunityName(communityName);
        	list.add(manager);
		}
        return new PageInfo<>(list);
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

	@Override
	public Manager findOne(String managerId) {
		// TODO Auto-generated method stub
		return managerMapper.selectByPrimaryKey(managerId);
	}
}
