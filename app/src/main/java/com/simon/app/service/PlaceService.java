package com.simon.app.service;

import java.util.List;

import com.simon.dal.model.Place;
import com.simon.dal.vo.BaseQueryParam;

public interface PlaceService {
	/**
	 * 场所列表
	 * @return
	 */
	List<Place> list(BaseQueryParam baseQueryParam);
	
	/**
	 * 场所详情
	 * @param placeId
	 * @return
	 */
	Place findOne(String placeId);
}
