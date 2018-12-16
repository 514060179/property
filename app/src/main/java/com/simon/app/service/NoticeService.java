package com.simon.app.service;

import java.util.List;

import com.simon.dal.model.Notice;

public interface NoticeService {
	
	/**
	 * 列表
	 * @return
	 */
	List<Notice> list();
	
	/**
	 * 详情
	 * @param noticeId
	 * @return
	 */
	Notice findOne(String noticeId);
	
	int updateByPrimaryKeySelective(Notice record);
}
