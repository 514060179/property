package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.backstage.domain.model.Unit;
import com.simon.dal.model.PlaceRecord;
import com.simon.dal.vo.BaseQueryParam;
import com.simon.dal.vo.PlaceRecordStatisData;
import com.simon.dal.vo.PlaceRecordStatisQuery;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author fengtianying
 * @date 2019/4/15 9:21
 */
public interface PlaceRecordService {

    /**
     * 新增
     * @param placeRecord
     * @return
     */
    PlaceRecord add(PlaceRecord placeRecord);

    /**
     * 详情
     * @param placeRecordId
     * @return
     */
    PlaceRecord detail(String placeRecordId);

    /**
     * 修改
     * @param placeRecord
     * @return
     */
    int upd(PlaceRecord placeRecord);

    /**
     * 删除
     * @param placeId
     * @return
     */
    int del(String placeId);

    /**
     * 列表
     * @param baseQueryParam
     * @return
     */
    PageInfo<PlaceRecord> list(BaseQueryParam baseQueryParam);

    /**
     * 统计场所使用率
     * @param placeRecordStatisQuery
     * @return
     */
    List<PlaceRecordStatisData> statis(PlaceRecordStatisQuery placeRecordStatisQuery);

    /**
     * 列入物业收费
     *
     * @param communityId   社区id
     * @param placeRecordId 定场记录id
     * @param unitId        单元id
     * @param price         价格
     * @return
     */
    boolean creChargeItemRecord(String communityId,String userId, String placeRecordId, String unitId, BigDecimal price);

    /**
     * 查询定场收费记录
     *
     * @param placeRecordId
     * @return
     */
    ChargeItemRecord getChargeItemRecord(String placeRecordId);


    /**
     * 根据用户id获取单元
     *
     * @param userId
     * @return
     */
    Unit getUnitByUserId(String userId);

}
