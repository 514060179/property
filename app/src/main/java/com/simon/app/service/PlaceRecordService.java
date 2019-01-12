package com.simon.app.service;

import java.util.Date;
import java.util.List;

import com.simon.dal.model.PlaceRecord;

public interface PlaceRecordService {
	/**
	 * 我的场所列表
	 * @param placeRecord
	 * @return
	 */
	List<PlaceRecord> selfList(PlaceRecord placeRecord);
	/**
	 * 详情
	 * @param placeRecordId
	 * @return
	 */
	PlaceRecord findOne(String placeRecordId);
	/**
	 * 添加
	 * @param placeRecord
	 * @return
	 */
	PlaceRecord addPlaceRecord(PlaceRecord placeRecord);
	/**
	 * 查询某天的预约时间
	 * @param placeId
	 * @param orderDate
	 * @return
	 */
	List<PlaceRecord> findPlaceTime(String placeId,Date orderDate);
}
