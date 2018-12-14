package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Event;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;

public interface EventMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(String eventId);

    List<Event> selectByCondition(BaseQueryParam baseQueryParam);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKeyWithBLOBs(Event record);

    int updateByPrimaryKey(Event record);
}