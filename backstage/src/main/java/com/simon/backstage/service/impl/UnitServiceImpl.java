package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.UnitMapper;
import com.simon.backstage.dao.UserUnitMapper;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.model.UserUnit;
import com.simon.backstage.service.UnitService;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private UserUnitMapper userUnitMapper;
    
    @Override
    public Unit add(Unit unit) {
        unit.setUnitId(UUIDUtil.uidString());
        unitMapper.insertSelective(unit);
        return unit;
    }

    @Override
    public Unit detail(String unitId) {
        return unitMapper.selectByPrimaryKey(unitId);
    }

    @Override
    public int upd(Unit unit) {
        return unitMapper.updateByPrimaryKeySelective(unit);
    }

    @Override
    public int del(String unitId) {
        return unitMapper.deleteByPrimaryKey(unitId);
    }

    @Override
    public PageInfo<Unit> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(unitMapper.selectByCondition(baseQueryParam));
    }
    

	@Override
	public UserUnit addUser(UserUnit userUnit) {
		int result = userUnitMapper.insertSelective(userUnit);
		if(result > 0){
			return userUnit;
		}
		return null;
	}

    @Override
    public UserUnit findUserUnitByUnitId(String unitId) {
        return userUnitMapper.selectByUnitId(unitId);
    }

    @Override
	public int delUser(String unitId, String userId) {
		// TODO Auto-generated method stub
		return userUnitMapper.deleteByUser(unitId, userId);
	}
}
