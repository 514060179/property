package com.simon.app.service;

import java.util.List;

import com.simon.dal.model.Complain;

public interface ComplainService {
	/**
	 * 我的投诉/报修列表
	 * @param userId
	 * @return
	 */
	List<Complain> selfList(String userId);
	/**
	 * 详情
	 * @param complainId
	 * @return
	 */
	Complain findOne(String complainId);
	/**
	 * 添加
	 * @param complain
	 * @param paths 
	 * @return
	 */
	int addComplain(Complain complain, String paths);
	
	int updateByPrimaryKeySelective(Complain complain);
}
