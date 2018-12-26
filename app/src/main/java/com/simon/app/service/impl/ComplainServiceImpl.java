package com.simon.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simon.app.service.ComplainService;
import com.simon.dal.dao.ComplainMapper;
import com.simon.dal.dao.ImageMapper;
import com.simon.dal.model.Complain;
import com.simon.dal.model.Image;
import com.simon.dal.util.UUIDUtil;

@Transactional
@Service
public class ComplainServiceImpl implements ComplainService{

	@Autowired
	private ComplainMapper complainMapper;
	@Autowired
	private ImageMapper imageMapper;
	
	@Override
	public Complain findOne(String complainId) {
		
		return complainMapper.selectByPrimaryKey(complainId);
	}

	@Override
	public List<Complain> selfList(Complain complain) {
		
		return complainMapper.list(complain);
	}

	@Override
	public int addComplain(Complain complain, String paths) {
		if(paths != "" && paths != null){
    		String[] path = paths.split(",");
    		List<Image> list = new ArrayList<Image>();
    		for (String url : path) {
    			Image image = new Image();
    			image.setComplainId(complain.getComplainId());
				image.setImageId(UUIDUtil.uidString());
				image.setImageUrl(url);
				list.add(image);
			}
    		imageMapper.insertBatch(list);
    	}
		return complainMapper.insertSelective(complain);
	}

	@Override
	public int updateByPrimaryKeySelective(Complain complain) {
		// TODO Auto-generated method stub
		return complainMapper.updateByPrimaryKeySelective(complain);
	}
	
}
