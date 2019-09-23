package com.simon.backstage.service.impl;

import com.simon.backstage.domain.vo.PlaceRecordQueryParam;
import com.simon.backstage.service.ExcelPlaceRecordService;
import com.simon.dal.dao.PlaceRecordMapper;
import com.simon.dal.model.PlaceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelPlaceRecordServiceImpl implements ExcelPlaceRecordService {

    @Autowired
    private PlaceRecordMapper placeRecordMapper;

    @Override
    public List<PlaceRecord> excelPlaceRecord(PlaceRecordQueryParam placeRecordQueryParam) {
        return placeRecordMapper.selectByCondition(placeRecordQueryParam);
    }
}
