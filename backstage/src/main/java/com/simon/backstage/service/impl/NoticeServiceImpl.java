package com.simon.backstage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simon.backstage.service.NoticeService;
import com.simon.dal.dao.NoticeMapper;
import com.simon.dal.model.Notice;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public Notice add(Notice notice) {
		notice.setNoticeId(UUIDUtil.uidString());
		if(noticeMapper.insertSelective(notice) > 0){
			return notice;
		}
		return null;
	}

	@Override
	public int del(String noticeId) {
		return noticeMapper.deleteByPrimaryKey(noticeId);
	}

	@Override
	public int upd(Notice notice) {
		return noticeMapper.updateByPrimaryKeySelective(notice);
	}

	@Override
	public List<Notice> list(BaseClaims baseClaims) {
		return noticeMapper.list(baseClaims);
	}
}
