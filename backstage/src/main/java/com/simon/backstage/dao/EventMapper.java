package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Event;
import com.simon.dal.vo.BaseClaims;
import com.simon.dal.vo.EventQueryParam;

import java.util.List;

public interface EventMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(String eventId);

    List<Event> selectByCondition(EventQueryParam eventQueryParam);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKeyWithBLOBs(Event record);

    int updateByPrimaryKey(Event record);
}