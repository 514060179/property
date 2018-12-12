package com.simon.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simon.app.service.PlaceService;
import com.simon.dal.dao.PlaceMapper;
import com.simon.dal.model.Place;

@Service
public class PlaceServiceImpl implements PlaceService{
	
	@Autowired
	private PlaceMapper placeMapper;

	@Override
	public List<Place> list() {
		return placeMapper.list();
	}

	@Override
	public Place findOne(String placeId) {
		return placeMapper.selectByPrimaryKey(placeId);
	}
	
	
}
