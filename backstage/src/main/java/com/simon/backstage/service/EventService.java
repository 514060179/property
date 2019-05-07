package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Event;
import com.simon.backstage.domain.vo.EventQueryParam;
import com.simon.backstage.domain.vo.EventUpdParam;

/**
 * @author fengtianying
 * @date 2018/12/14 17:13
 */
public interface EventService {

    /**
     * 新增
     * @param event
     * @return
     */
    Event add(Event event);

    /**
     * 新增
     * @param eventId
     * @return
     */
    Event detail(String eventId);

    /**
     * 修改
     * @param event
     * @return
     */
    int upd(Event event);

    /**
     * 删除
     * @param eventId
     * @return
     */
    int del(String eventId);

    /**
     * 列表
     * @param eventQueryParam
     * @return
     */
    PageInfo<Event> list(EventQueryParam eventQueryParam);

	/**
     * 处理事件
     * @param event
     * @return
     */
    int changeStatus(Event event);
}
