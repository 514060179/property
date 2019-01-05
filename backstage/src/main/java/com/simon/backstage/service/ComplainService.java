package com.simon.backstage.service;

import java.util.List;

import com.simon.dal.model.Complain;
import com.simon.dal.vo.BaseClaims;

public interface ComplainService {
	/**
	 * 投诉/报修列表
	 * @param baseClaims
	 * @return
	 */
	List<Complain> list(BaseClaims baseClaims);
	
	/**
	 * 改变投诉/报修的状态
	 * @param complain
	 * @param userId	处理人id
	 * @return
	 */
	int changStatus(Complain complain, String managerId);
}
