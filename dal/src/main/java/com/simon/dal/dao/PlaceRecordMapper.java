package com.simon.dal.dao;

import java.util.Date;
import java.util.List;

import com.simon.dal.vo.BaseQueryParam;
import org.apache.ibatis.annotations.Param;

import com.simon.dal.model.PlaceRecord;

public interface PlaceRecordMapper {
    int deleteByPrimaryKey(String recordId);

    int insert(PlaceRecord record);

    int insertSelective(PlaceRecord record);

    PlaceRecord selectByPrimaryKey(String recordId);

    int updateByPrimaryKeySelective(PlaceRecord record);

    int updateByPrimaryKey(PlaceRecord record);
    
    List<PlaceRecord> list(PlaceRecord record);

    List<PlaceRecord> selectByCondition(BaseQueryParam baseQueryParam);

    int findPlaceTime(PlaceRecord placeRecord);
}