package com.simon.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.simon.app.service.ComplainService;
import com.simon.dal.constant.Type;
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
	public int addComplain(Complain complain) {
		if(!complain.getImages().isEmpty() && complain.getImages().get(0)!=null){
			Images images = complain.getImages().get(0);
    		String[] imageUrl = images.getImageUrl().split(",");
    		String[] imageThumbnail = images.getImageThumbnail().split(",");
    		List<Images> list = new ArrayList<Images>();
    		for (int i = 0; i<imageUrl.length; i++) {
    			Images image = new Images();
    			image.setImageId(UUIDUtil.uidString());
    			image.setObjectId(complain.getComplainId());
				image.setImageUrl(imageUrl[i]);
				image.setImageThumbnail(imageThumbnail[i]);
				image.setImageType(Type.IMAGE_TYPE_COMPLAIN);
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
