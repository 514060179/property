package com.simon.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simon.app.service.ImageService;
import com.simon.dal.dao.ImageMapper;
import com.simon.dal.model.Image;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageMapper imageMapper;
	
	@Override
	public int insertSelective(Image record) {
		// TODO Auto-generated method stub
		return imageMapper.insertSelective(record);
	}

}