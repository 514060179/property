package com.simon.app.service;

import java.util.List;

import com.simon.dal.model.Complain;
import com.simon.dal.vo.BaseClaims;

public interface ComplainService {
	/**
	 * 我的投诉/报修列表
	 * @param userId
	 * @return
	 */
	List<Complain> list(BaseClaims baseClaims);
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
	int addComplain(Complain complain);
	
	int updateByPrimaryKeySelective(Complain complain);
}
