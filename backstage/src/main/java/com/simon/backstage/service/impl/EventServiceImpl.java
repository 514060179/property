package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.EventMapper;
import com.simon.backstage.domain.model.Event;
import com.simon.backstage.service.EventService;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 事件管理业务层
 * @author fengtianying
 * @date 2018/12/14 17:15
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper eventMapper;

    @Override
    public Event add(Event event) {
        event.setEventId(UUIDUtil.uidString());
        eventMapper.insertSelective(event);
        return event;
    }

    @Override
    public Event detail(String eventId) {
        return eventMapper.selectByPrimaryKey(eventId);
    }

    @Override
    public int upd(Event event) {
        return eventMapper.updateByPrimaryKeySelective(event);
    }

    @Override
    public int del(String eventId) {
        return eventMapper.deleteByPrimaryKey(eventId);
    }

    @Override
    public PageInfo<Event> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(eventMapper.selectByCondition(baseQueryParam));
    }
}
