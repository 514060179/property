package com.simon.backstage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simon.backstage.service.NoticeService;
import com.simon.dal.constant.Type;
import com.simon.dal.dao.ImageMapper;
import com.simon.dal.dao.NoticeMapper;
import com.simon.dal.model.Images;
import com.simon.dal.model.Notice;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private ImageMapper imageMapper;
	
	@Override
	@Transactional
	public Notice add(Notice notice) {
		notice.setNoticeId(UUIDUtil.uidString());
		if(!notice.getNoticeImage().isEmpty() && notice.getNoticeImage().get(0)!=null){
			String[] imageUrl = notice.getNoticeImage().get(0).getImageUrl().split(",");
			String[] imageThumbnail = notice.getNoticeImage().get(0).getImageThumbnail().split(",");
			List<Images> list = new ArrayList<>();
			for(int i = 0; i < imageUrl.length; i++){
				Images image = new Images();
				image.setImageId(UUIDUtil.uidString());
				image.setImageUrl(imageUrl[i]);
				image.setImageThumbnail(imageThumbnail[i]);
				image.setImageType(Type.IMAGE_TYPE_NOTICE);
				image.setObjectId(notice.getNoticeId());
				list.add(image);
			}
			imageMapper.insertBatch(list);
		}
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
