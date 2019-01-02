package com.simon.backstage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.simon.backstage.service.ComplainService;
import com.simon.dal.dao.ComplainMapper;
import com.simon.dal.model.Complain;
import com.simon.dal.vo.BaseQueryParam;

@Service
public class ComplainServiceImpl implements ComplainService {

	@Autowired
	private ComplainMapper complainMapper;
	
	@Override
	public List<Complain> list(BaseQueryParam baseQueryParam) {
		PageHelper.startPage(baseQueryParam.getPageNo(), baseQueryParam.getPageSize());
		return complainMapper.list(baseQueryParam);
	}
	
}
