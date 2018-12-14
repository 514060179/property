package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Unit;
import com.simon.dal.vo.BaseQueryParam;

public interface UnitService {


    /**
     * 新增
     * @param unit
     * @return
     */
    Unit add(Unit unit);

    /**
     * 新增
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
     * @param baseQueryParam
     * @return
     */
    PageInfo<Unit> list(BaseQueryParam baseQueryParam);
}
