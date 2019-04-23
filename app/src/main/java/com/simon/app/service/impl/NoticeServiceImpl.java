package com.simon.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.simon.app.service.NoticeService;
import com.simon.dal.dao.NoticeMapper;
import com.simon.dal.model.Notice;
import com.simon.dal.vo.BaseClaims;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public List<Notice> list(BaseClaims baseClaims) {
		PageHelper.startPage(baseClaims.getPageNo(), baseClaims.getPageSize());
		return noticeMapper.list(baseClaims,null);
	}
	
	@Override
	public Notice findOne(String noticeId) {
		
		return noticeMapper.selectByPrimaryKey(noticeId);
	}

	@Override
	public int updateByPrimaryKeySelective(Notice record) {
		
		return noticeMapper.updateByPrimaryKeySelective(record);
	}
}
