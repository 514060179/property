package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.BuildingMapper;
import com.simon.backstage.dao.EventMapper;
import com.simon.backstage.domain.model.Enclosure;
import com.simon.backstage.domain.model.Event;
import com.simon.backstage.service.EventService;
import com.simon.dal.constant.Type;
import com.simon.dal.util.UUIDUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.simon.backstage.domain.vo.EventQueryParam;
import com.simon.backstage.domain.vo.EventUpdParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private BuildingMapper buildingMapper;
    @Override
    @Transactional
    public Event add(Event event) {
        event.setEventId(UUIDUtil.uidString());
        eventMapper.insertSelective(event);
        List<Enclosure> enclosureList = new ArrayList<>();
        if (event.getReports()!=null&&event.getReports().size()>0){
            event.getReports().forEach(s -> {
                Enclosure enclosure = new Enclosure();
                enclosure.setEnclosureId(UUIDUtil.uidString());
                enclosure.setObjectId(event.getEventId());
                enclosure.setEnclosureObjectType(Type.ENCLOSURE_OBJECT_TYPE_EVENT);
                enclosure.setEnclosureType(Type.ENCLOSURE_TYPE_PDF);
                enclosure.setEnclosureUrl(s);
                enclosureList.add(enclosure);
            });
            buildingMapper.insertEnclosures(enclosureList);
        }
        return event;
    }

    @Override
    public Event detail(String eventId) {
        return eventMapper.selectByPrimaryKey(eventId);
    }

    @Override
    public int upd(EventUpdParam eventUpdParam) {
        List<Enclosure> enclosureList = new ArrayList<>();
        if (eventUpdParam.getReports()!=null&&eventUpdParam.getReports().size()>0){
            eventUpdParam.getReports().forEach(s -> {
                Enclosure enclosure = new Enclosure();
                enclosure.setEnclosureId(UUIDUtil.uidString());
                enclosure.setObjectId(eventUpdParam.getEventId());
                enclosure.setEnclosureUrl(s);
                enclosure.setEnclosureType(Type.ENCLOSURE_TYPE_PDF);
                enclosure.setEnclosureObjectType(Type.ENCLOSURE_OBJECT_TYPE_EVENT);
                enclosureList.add(enclosure);
            });
            buildingMapper.delEnclosure(eventUpdParam.getEventId(),Type.ENCLOSURE_OBJECT_TYPE_EVENT);
            buildingMapper.insertEnclosures(enclosureList);
        }
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
