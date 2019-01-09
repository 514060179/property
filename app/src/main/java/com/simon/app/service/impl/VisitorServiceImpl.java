package com.simon.app.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simon.app.service.VisitorService;
import com.simon.dal.dao.VisitorMapper;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.VisitorWithToken;

@Service
public class VisitorServiceImpl implements VisitorService {

	@Autowired
	private VisitorMapper visitorMapper;
	
	@Override
	public int record(VisitorWithToken visitor) {
		visitor.setVisitorId(UUIDUtil.uidString());
		return visitorMapper.insertSelective(visitor);
	}
	 
}
