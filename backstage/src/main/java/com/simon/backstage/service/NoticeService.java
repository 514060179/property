package com.simon.backstage.service;

import java.util.List;

import com.simon.dal.model.Notice;
import com.simon.dal.vo.BaseClaims;

public interface NoticeService {
	
	Notice add(Notice notice);
	
	int del(String noticeId);
	
	int upd(Notice notice);
	
	List<Notice> list(BaseClaims baseClaims);
}
