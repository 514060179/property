package com.simon.backstage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.simon.backstage.dao.ManagerMapper;
import com.simon.backstage.domain.model.Manager;
import com.simon.backstage.service.ComplainService;
import com.simon.dal.dao.ComplainMapper;
import com.simon.dal.model.Complain;
import com.simon.dal.vo.BaseClaims;

@Service
public class ComplainServiceImpl implements ComplainService {

	@Autowired
	private ComplainMapper complainMapper;
	@Autowired
	private ManagerMapper managerMapper;
	
	@Override
	public List<Complain> list(BaseClaims baseClaims) {
		PageHelper.startPage(baseClaims.getPageNo(), baseClaims.getPageSize());
		return complainMapper.list(baseClaims);
	}

	@Override
	public int changStatus(Complain complain, String managerId) {
		Manager manager = managerMapper.selectByPrimaryKey(managerId);
		complain.setComplainLiaisonsName(manager.getName());
		complain.setComplainFinishTime(new Date());
		return complainMapper.updateByPrimaryKeySelective(complain);
	}
	
}
