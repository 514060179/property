package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Building;
import com.simon.dal.vo.BaseClaims;

/**
 * @author fengtianying
 * @date 2018/12/14 16:57
 */
public interface BuildingService {


    /**
     * 新增
     * @param building
     * @return
     */
    Building add(Building building);

    /**
     * 新增
     * @param buildingId
     * @return
     */
    Building detail(String buildingId);

    /**
     * 修改
     * @param building
     * @return
     */
    int upd(Building building);

    /**
     * 删除
     * @param buildingId
     * @return
     */
    int del(String buildingId);

    /**
     * 列表
     * @param BaseClaims
     * @return
     */
    PageInfo<Building> list(BaseClaims BaseClaims);

}
