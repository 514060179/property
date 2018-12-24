package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.dal.model.Place;
import com.simon.dal.vo.BaseQueryParam;

public interface PlaceService {

    /**
     * 新增
     * @param place
     * @return
     */
    Place add(Place place);

    /**
     * 新增
     * @param placeId
     * @return
     */
    Place detail(String placeId);

    /**
     * 修改
     * @param place
     * @return
     */
    int upd(Place place);

    /**
     * 删除
     * @param placeId
     * @return
     */
    int del(String placeId);

    /**
     * 列表
     * @param baseQueryParam
     * @return
     */
    PageInfo<Place> list(BaseQueryParam baseQueryParam);
}
