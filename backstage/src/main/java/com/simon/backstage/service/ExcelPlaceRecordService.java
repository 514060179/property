package com.simon.backstage.service;

import com.simon.backstage.domain.vo.PlaceRecordQueryParam;
import com.simon.dal.model.PlaceRecord;

import java.util.List;

public interface ExcelPlaceRecordService {
    List<PlaceRecord> excelPlaceRecord(PlaceRecordQueryParam placeRecordQueryParam);
}
