package com.simon.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.simon.dal.constant.Type;
import com.simon.dal.dao.ImageMapper;
import com.simon.dal.model.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simon.app.service.PlaceRecordService;
import com.simon.dal.dao.PlaceRecordMapper;
import com.simon.dal.model.PlaceRecord;

@Service
public class PlaceRecordServiceImpl implements PlaceRecordService {
	
	@Autowired
	private PlaceRecordMapper placeRecordMapper;

	@Autowired
	private ImageMapper imageMapper;

	@Override
	public List<PlaceRecord> selfList(PlaceRecord placeRecord) {
		return placeRecordMapper.list(placeRecord);
	}

	@Override
	public PlaceRecord findOne(String placeRecordId) {
		// TODO Auto-generated method stub
		PlaceRecord placeRecord = placeRecordMapper.selectByPrimaryKey(placeRecordId);
		if (!Objects.isNull(placeRecord)){
			List<Images> imagesList = imageMapper.findListById(placeRecord.getPlaceId(), Type.IMAGE_TYPE_PLACE);
			placeRecord.getPlace().setImages(imagesList);
		}
		return placeRecord;
	}

	@Override
	public PlaceRecord addPlaceRecord(PlaceRecord placeRecord) {
		int result = placeRecordMapper.insertSelective(placeRecord);
		if(result > 0){
			return placeRecord;
		}
		return null;
	}

	@Override
	public int findPlaceTime(PlaceRecord placeRecord) {
		return placeRecordMapper.findPlaceTime(placeRecord);
	}
	
}
