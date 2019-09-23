package com.simon.backstage.service;

import com.simon.backstage.domain.model.Event;
import com.simon.backstage.domain.vo.EventQueryParam;

import java.util.List;

public interface ExcelEventService {
    List<Event> excelEvent(EventQueryParam eventQueryParam);
}
