package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.service.PlaceService;
import com.simon.dal.dao.PlaceMapper;
import com.simon.dal.model.Place;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceMapper placeMapper;
    @Override
    public Place add(Place place) {
        place.setPlaceId(UUIDUtil.uidString());
        int i = placeMapper.insertSelective(place);
        if (i>0){
            return place;
        }else{
            return null;
        }
    }

    @Override
    public Place detail(String placeId) {
        return placeMapper.selectByPrimaryKey(placeId);
    }

    @Override
    public int upd(Place place) {
        return placeMapper.updateByPrimaryKeySelective(place);
    }

    @Override
    public int del(String placeId) {
        return placeMapper.deleteByPrimaryKey(placeId);
    }

    @Override
    public PageInfo<Place> list(BaseClaims baseClaims) {
        PageHelper.startPage(baseClaims.getPageNo(),baseClaims.getPageSize());
        return new PageInfo<>(placeMapper.list(baseClaims));
    }
}
