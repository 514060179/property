package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.ChargeItem;
import com.simon.dal.vo.BaseQueryParam;

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
     * 新增
     * @param chargeItemId
     * @return
     */
    ChargeItem detail(String chargeItemId);

    /**
     * 修改
     * @param chargeItem
     * @return
     */
    int upd(ChargeItem chargeItem);

    /**
     * 删除
     * @param chargeItemId
     * @return
     */
    int del(String chargeItemId);

    /**
     * 列表
     * @param baseQueryParam
     * @return
     */
    PageInfo<ChargeItem> list(BaseQueryParam baseQueryParam);
}
