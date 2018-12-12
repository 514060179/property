package com.simon.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simon.app.service.PlaceRecordService;
import com.simon.dal.dao.PlaceRecordMapper;
import com.simon.dal.model.PlaceRecord;

@Service
public class PlaceRecordServiceImpl implements PlaceRecordService {
	
	@Autowired
	private PlaceRecordMapper placeRecordMapper;

	@Override
	public List<PlaceRecord> selfList(PlaceRecord placeRecord) {
		return placeRecordMapper.list(placeRecord);
	}

	@Override
	public PlaceRecord findOne(String placeRecordId) {
		// TODO Auto-generated method stub
		return placeRecordMapper.selectByPrimaryKey(placeRecordId);
	}

	@Override
	public int addPlaceRecord(PlaceRecord placeRecord) {
		// TODO Auto-generated method stub
		return placeRecordMapper.insertSelective(placeRecord);
	}
	
	
}
