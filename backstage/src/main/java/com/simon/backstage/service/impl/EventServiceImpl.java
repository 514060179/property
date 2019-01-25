package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.EventMapper;
import com.simon.backstage.dao.ManagerMapper;
import com.simon.backstage.domain.model.Event;
import com.simon.backstage.service.EventService;
import com.simon.dal.util.UUIDUtil;

import java.util.Date;

import com.simon.backstage.domain.vo.EventQueryParam;
import com.simon.backstage.domain.vo.EventUpdParam;
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
    @Autowired
    private ManagerMapper managerMapper;

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
    public int upd(EventUpdParam eventUpdParam) {
        return eventMapper.updateSelective(eventUpdParam);
    }

    @Override
    public int del(String eventId) {
        return eventMapper.deleteByPrimaryKey(eventId);
    }

    @Override
    public PageInfo<Event> list(EventQueryParam eventQueryParam) {
        PageHelper.startPage(eventQueryParam.getPageNo(),eventQueryParam.getPageSize());
        return new PageInfo<>(eventMapper.selectByCondition(eventQueryParam));
    }

	@Override
	public int changeStatus(Event event) {
		if(event.getEventStatus()==2){//事件完成
			event.setEventFinishDate(new Date());
		}
		return eventMapper.updateByPrimaryKeySelective(event);
	}
}
