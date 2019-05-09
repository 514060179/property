package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.dal.model.PlaceRecord;
import com.simon.dal.vo.BaseQueryParam;
import com.simon.dal.vo.PlaceRecordStatisData;
import com.simon.dal.vo.PlaceRecordStatisQuery;

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

}