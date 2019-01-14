package com.simon.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.simon.app.service.ComplainService;
import com.simon.dal.dao.ComplainMapper;
import com.simon.dal.dao.ImageMapper;
import com.simon.dal.model.Complain;
import com.simon.dal.model.Images;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;

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
	public List<Complain> list(BaseClaims baseClaims) {
		PageHelper.startPage(baseClaims.getPageNo(), baseClaims.getPageSize());
		return complainMapper.list(baseClaims);
	}

	@Transactional
	@Override
	public int addComplain(Complain complain, String paths) {
		if(paths != "" && paths != null){
    		String[] path = paths.split(",");
    		List<Images> list = new ArrayList<Images>();
    		for (String url : path) {
    			Images image = new Images();
    			image.setImageId(UUIDUtil.uidString());
    			image.setObjectId(complain.getComplainId());
				image.setImageUrl(url);
				image.setImageType(0);
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
