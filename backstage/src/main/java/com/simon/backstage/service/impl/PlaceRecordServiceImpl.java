package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.service.PlaceRecordService;
import com.simon.dal.dao.PlaceRecordMapper;
import com.simon.dal.model.PlaceRecord;
import com.simon.dal.vo.BaseQueryParam;
import com.simon.dal.vo.PlaceRecordStatisData;
import com.simon.dal.vo.PlaceRecordStatisQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fengtianying
 * @date 2019/4/16 10:21
 */
@Service
public class PlaceRecordServiceImpl implements PlaceRecordService {

    @Autowired
    private PlaceRecordMapper placeRecordMapper;
    @Override
    public PlaceRecord add(PlaceRecord placeRecord) {
        return null;
    }

    @Override
    public PlaceRecord detail(String placeRecordId) {
        return placeRecordMapper.selectByPrimaryKey(placeRecordId);
    }

    @Override
    public int upd(PlaceRecord placeRecord) {
        return placeRecordMapper.updateByPrimaryKeySelective(placeRecord);
    }

    @Override
    public int del(String placeId) {
        return placeRecordMapper.deleteByPrimaryKey(placeId);
    }

    @Override
    public PageInfo<PlaceRecord> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(placeRecordMapper.selectByCondition(baseQueryParam));
    }

    @Override
    public List<PlaceRecordStatisData> statis(PlaceRecordStatisQuery placeRecordStatisQuery) {
        return placeRecordMapper.statis(placeRecordStatisQuery);
    }
}
