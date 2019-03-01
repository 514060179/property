package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.model.UserUnit;
import com.simon.dal.vo.BaseClaims;

public interface UnitService {


    /**
     * 新增
     * @param unit
     * @return
     */
    Unit add(Unit unit);

    /**
     * 详情
     * @param unitId
     * @return
     */
    Unit detail(String unitId);

    /**
     * 修改
     * @param unit
     * @return
     */
    int upd(Unit unit);

    /**
     * 删除
     * @param unitId
     * @return
     */
    int del(String unitId);

    /**
     * 列表
     * @param baseClaims
     * @return
     */
    PageInfo<Unit> list(BaseClaims baseClaims);
    
    /**
     * 房间住户添加
     * @param userUnit
     * @return
     */
    UserUnit addUser(UserUnit userUnit);

    /**
     * 房间住户添加
     * @param unitId
     * @return
     */
    UserUnit findUserUnitByUnitId(String unitId);

    /**
     * 房间住户删除
     * @param unitId
     * @param userId
     * @return
     */
	int delUser(String unitId, String userId);
}
