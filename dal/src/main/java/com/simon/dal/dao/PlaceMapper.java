package com.simon.dal.dao;

import java.util.List;

import com.simon.dal.model.Place;
import com.simon.dal.vo.BaseQueryParam;

public interface PlaceMapper {
    int deleteByPrimaryKey(String placeId);

    int insert(Place record);

    int insertSelective(Place record);

    Place selectByPrimaryKey(String placeId);

    int updateByPrimaryKeySelective(Place record);

    int updateByPrimaryKeyWithBLOBs(Place record);

    int updateByPrimaryKey(Place record);
    
    List<Place> list(BaseQueryParam baseQueryParam);
}