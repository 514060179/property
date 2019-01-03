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
}
