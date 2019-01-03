package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.UnitMapper;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.service.UnitService;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitMapper unitMapper;
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
    public PageInfo<Unit> list(BaseClaims baseClaims) {
        PageHelper.startPage(baseClaims.getPageNo(),baseClaims.getPageSize());
        return new PageInfo<>(unitMapper.selectByCondition(baseClaims));
    }
}
