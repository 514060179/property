package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.model.UserUnit;
import com.simon.dal.model.User;
import com.simon.dal.vo.BaseClaims;
import com.simon.dal.vo.BaseQueryParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * 更改用户绑定单元信息
     * @param userUnit
     * @return
     */
    int updUserUnit(UserUnit userUnit);

    /**
     * 删除
     * @param unitId
     * @return
     */
    int del(String unitId);

    /**
     * 列表
     * @param baseQueryParam
     * @return
     */
    PageInfo<Unit> list(BaseQueryParam baseQueryParam);
    
    /**
     * 房间住户添加
     * @param userUnit
     * @return
     */
    UserUnit addUser(UserUnit userUnit);
    /**
     * 批量房间住户绑定
     * @param userUnitList
     * @return
     */
    int batchAddUser(List<UserUnit> userUnitList);

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
    /**
     * 单元住户列表
     * @param unitId
     * @return
     */
	List<User> unitUserList(String unitId);

    /**
     * excel导入数据
     * @param request
     * @param buildingId
     * @return
     */
	String importExcel(HttpServletRequest request,String communityId, String buildingId);
}
