package com.simon.backstage.service.impl;

import com.simon.backstage.dao.EventMapper;
import com.simon.backstage.domain.model.Event;
import com.simon.backstage.domain.vo.EventQueryParam;
import com.simon.backstage.service.ExcelEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelEventServiceImpl implements ExcelEventService {

    @Autowired
    private EventMapper eventMapper;

    @Override
    public List<Event> excelEvent(EventQueryParam eventQueryParam) {
        return eventMapper.selectByCondition(eventQueryParam);
    }
}
