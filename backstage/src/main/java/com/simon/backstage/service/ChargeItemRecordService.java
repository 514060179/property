package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.AdvanceMoney;
import com.simon.backstage.domain.model.AdvanceRecord;
import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;

/**
 * @author fengtianying
 * @date 2019/1/27 14:41
 */
public interface ChargeItemRecordService {

    /**
     * 新增
     * @param chargeItemRecord
     * @return
     */
    ChargeItemRecord add(ChargeItemRecord chargeItemRecord);
    /**
     * 批量新增
     * @param chargeItemRecordList
     * @return
     */
    int addBatch(List<ChargeItemRecord> chargeItemRecordList,List<AdvanceMoney> advanceMonies,List<AdvanceRecord> advanceRecords);

    /**
     * 详情
     * @param recordId
     * @return
     */
    ChargeItemRecord detail(String recordId);

    /**
     * 修改
     * @param chargeItemRecord
     * @return
     */
    int upd(ChargeItemRecord chargeItemRecord);

    /**
     * 删除
     * @param recordId
     * @return
     */
    int del(String recordId);

    /**
     * 列表
     * @param baseQueryParam
     * @return
     */
    PageInfo<ChargeItemRecord> list(BaseQueryParam baseQueryParam);
}
