package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Event;

public interface EventMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(String eventId);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKeyWithBLOBs(Event record);

    int updateByPrimaryKey(Event record);
}