package com.simon.app.service;

import java.util.List;

import com.simon.dal.model.Notice;

public interface NoticeService {
	
	List<Notice> list();
	
	/**
	 * 详情
	 * @param noticeId
	 * @return
	 */
	Notice findOne(String noticeId);
}
