package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.ChargeItem;
import com.simon.backstage.domain.vo.QueryWithIdParam;
import com.simon.backstage.domain.vo.UnitItemWithUser;
import com.simon.backstage.domain.vo.UnitWithItem;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;

/**
 * @author fengtianying
 * @date 2019/1/26 16:13
 */
public interface ChargeItemService {

    /**
     * 新增
     * @param chargeItem
     * @return
     */
    ChargeItem add(ChargeItem chargeItem);

    /**
     * 详情
     * @param itemId
     * @return
     */
    ChargeItem detail(String itemId);

    /**
     * 获取收费项
     * @return
     */
    ChargeItem findItemIdAndUnitId(String itemId,String unitId);
    /**
     * 修改
     * @param chargeItem
     * @return
     */
    int upd(ChargeItem chargeItem);

    /**
     * 删除
     * @param itemId
     * @return
     */
    int del(String itemId);
    /**
     * 删除单元的收费项目
     * @param unitItemId
     * @return
     */
    int delUnitChargeItem(String unitItemId);

    /**
     * 列表
     * @param baseQueryParam
     * @return
     */
    PageInfo<ChargeItem> list(BaseQueryParam baseQueryParam);

    /**
     * 单元添加收费项目
     * @param unitWithItemList
     * @return
     */
    int unitAddItem(List<UnitWithItem> unitWithItemList);

    /**
     * 单元收费项目列表
     * @param queryWithIdParam
     * @return
     */
    PageInfo<ChargeItem> unitItemList(QueryWithIdParam queryWithIdParam);

    /**
     * 获取所有周期性收费项目
     * @return
     */
    List<UnitItemWithUser> cycleUnitItem();
}
