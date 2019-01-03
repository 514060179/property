package com.simon.app.service;

import java.util.List;

import com.simon.dal.model.Notice;
import com.simon.dal.vo.BaseClaims;

public interface NoticeService {
	
	/**
	 * 列表
	 * @param baseClaims
	 * @return
	 */
	List<Notice> list(BaseClaims baseClaims);
	
	/**
	 * 详情
	 * @param noticeId
	 * @return
	 */
	Notice findOne(String noticeId);
	
	int updateByPrimaryKeySelective(Notice record);
}
