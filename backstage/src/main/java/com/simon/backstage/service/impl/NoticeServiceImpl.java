package com.simon.backstage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.constant.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.simon.backstage.service.NoticeService;
import com.simon.dal.constant.Type;
import com.simon.dal.dao.CommunityMapper;
import com.simon.dal.dao.ImageMapper;
import com.simon.dal.dao.NoticeMapper;
import com.simon.dal.model.Community;
import com.simon.dal.model.Images;
import com.simon.dal.model.Notice;
import com.simon.dal.util.JPushUtil;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;

import cn.jpush.api.push.model.PushPayload;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private ImageMapper imageMapper;
	
	@Autowired
	private CommunityMapper communityMapper;
	
	@Override
	@Transactional
	public Notice add(Notice notice) {
		notice.setNoticeId(UUIDUtil.uidString());
		if(!notice.getNoticeImage().isEmpty() && notice.getNoticeImage().get(0)!=null){
			List<Images> list = new ArrayList<>();
			notice.getNoticeImage().forEach(images -> {
				Images image = new Images();
				image.setImageId(UUIDUtil.uidString());
				image.setImageUrl(images.getImageUrl());
				image.setImageThumbnail(images.getImageThumbnail());
				image.setImageType(Type.IMAGE_TYPE_NOTICE);
				image.setObjectId(notice.getNoticeId());
				list.add(image);
			});
			imageMapper.insertBatch(list);
			notice.setNoticeImage(list);
		}
		if(noticeMapper.insertSelective(notice) > 0){
			String target = "";
			if(!StringUtils.isEmpty(notice.getCommunityId())){
				target = notice.getCommunityId();
			}else if(!StringUtils.isEmpty(notice.getBuildingId())){
				target = notice.getBuildingId();
			}else{
				target = communityMapper.findId();
			}
			Map<String, String> map = new HashMap<>();
			map.put("noticeId", notice.getNoticeId());
			map.put("noticeType", notice.getNoticeType()+"");
			map.put("noticeTitle", notice.getNoticeTitle());
			final String t = target;
			//app推送
			if (notice.getNoticeType()== Status.noticeTypeApp){
				new Thread(()->{
					PushPayload pu = JPushUtil.buildPushObjectByAlias(notice.getNoticeTitle(),true, t, map, 1);
					JPushUtil.push(pu);
				}).start();
			}
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
	public PageInfo<Notice> list(BaseClaims baseClaims) {
		PageHelper.startPage(baseClaims.getPageNo(),baseClaims.getPageSize());
		return new PageInfo<>(noticeMapper.list(baseClaims,null));
	}

	@Override
	public List<Notice> touchList(BaseClaims baseClaims) {
		return noticeMapper.touchList(baseClaims);
	}

	@Override
	public Notice findOne(String noticeId) {
		// TODO Auto-generated method stub
		return noticeMapper.selectByPrimaryKey(noticeId);
	}
}
