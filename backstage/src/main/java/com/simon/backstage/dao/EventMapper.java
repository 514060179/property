package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Event;
import com.simon.backstage.domain.vo.EventQueryParam;
import com.simon.backstage.domain.vo.EventUpdParam;

import java.util.List;

public interface EventMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(String eventId);

    List<Event> selectByCondition(EventQueryParam eventQueryParam);

    List<Event> selectRemindEvent();

    int updateByPrimaryKeySelective(Event record);

    int updateSelective(Event event);

    int updateByPrimaryKeyWithBLOBs(Event record);

    int updateByPrimaryKey(Event record);
}