package com.simon.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simon.app.service.ComplainService;
import com.simon.dal.dao.ComplainMapper;
import com.simon.dal.model.Complain;

@Service
public class ComplainServiceImpl implements ComplainService{

	@Autowired
	private ComplainMapper complainMapper;
	
	@Override
	public Complain findOne(String complainId) {
		
		return complainMapper.selectByPrimaryKey(complainId);
	}

	@Override
	public List<Complain> selfList(Complain complain) {
		
		return complainMapper.list(complain);
	}

	@Override
	public int addComplain(Complain complain) {
		
		return complainMapper.insertSelective(complain);
	}

	@Override
	public int updateByPrimaryKeySelective(Complain complain) {
		// TODO Auto-generated method stub
		return complainMapper.updateByPrimaryKeySelective(complain);
	}
	
}
