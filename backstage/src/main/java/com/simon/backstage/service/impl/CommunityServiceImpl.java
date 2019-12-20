package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.BuildingMapper;
import com.simon.backstage.domain.model.BuildingChild;
import com.simon.backstage.domain.model.BuildingPart;
import com.simon.backstage.domain.model.Enclosure;
import com.simon.backstage.service.CommunityService;
import com.simon.dal.constant.Type;
import com.simon.dal.dao.CommunityMapper;
import com.simon.dal.model.Community;
import com.simon.dal.model.CommunityChild;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {


    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private BuildingMapper buildingMapper;
    @Override
    @Transactional
    public Community add(Community community) {
        String communityId = UUIDUtil.uidString();
        community.setCommunityId(communityId);
        if (communityMapper.insertSelective(community)>0){
            List<Enclosure> enclosureList = new ArrayList<>();
            List<String> pdfList = community.getCommonPdf();
            if (pdfList!=null&&pdfList.size()>0){
                community.getCommonPdf().forEach(s -> {
                    Enclosure enclosure = new Enclosure();
                    enclosure.setEnclosureUrl(s);
                    enclosure.setEnclosureId(UUIDUtil.uidString());
                    enclosure.setObjectId(community.getCommunityId());
                    enclosure.setEnclosureObjectType(Type.ENCLOSURE_OBJECT_TYPE_COMMUNITY);
                    enclosure.setEnclosureType(Type.ENCLOSURE_TYPE_PDF);
                    enclosureList.add(enclosure);
                });
                buildingMapper.insertEnclosures(enclosureList);
            }
            List<CommunityChild> communityChildList = community.getCommunityChildList();
            if (communityChildList != null && !communityChildList.isEmpty()) {
                communityMapper.delCommunityChildByCommunityId(communityId);
                communityChildList.forEach(communityChild -> communityChild.setCommunityId(communityId));
                communityMapper.batchAddCommunityChild(communityChildList);
            }
        }
        return community;
    }

    @Override
    public Community detail(String communityId) {
        return communityMapper.selectByPrimaryKey(communityId);
    }

    @Override
    @Transactional
    public int upd(Community community) {
        int i = 0 ;
        if ( (i = communityMapper.updateByPrimaryKeySelective(community))>0){
            List<Enclosure> enclosureList = new ArrayList<>();
            List<String> pdfList = community.getCommonPdf();
            if (pdfList!=null&&pdfList.size()>0){
                pdfList.forEach(s -> {
                    Enclosure enclosure = new Enclosure();
                    enclosure.setEnclosureId(UUIDUtil.uidString());
                    enclosure.setEnclosureUrl(s);
                    enclosure.setObjectId(community.getCommunityId());
                    enclosure.setEnclosureObjectType(Type.ENCLOSURE_OBJECT_TYPE_COMMUNITY);
                    enclosure.setEnclosureType(Type.ENCLOSURE_TYPE_PDF);
                    enclosureList.add(enclosure);
                });
                buildingMapper.delEnclosure(community.getCommunityId(),Type.ENCLOSURE_OBJECT_TYPE_COMMUNITY);
                buildingMapper.insertEnclosures(enclosureList);
            }

            List<CommunityChild> communityChildList = community.getCommunityChildList();
            if (communityChildList != null && !communityChildList.isEmpty()) {
                communityMapper.delCommunityChildByCommunityId(community.getCommunityId());
                communityChildList.forEach(communityChild -> communityChild.setCommunityId(community.getCommunityId()));
                communityMapper.batchAddCommunityChild(communityChildList);
            }
        }
        return i;
    }

    @Override
    public int del(String communityId) {
        return communityMapper.deleteByPrimaryKey(communityId);
    }

    @Override
    public PageInfo<Community> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(communityMapper.list(baseQueryParam));
    }

    @Override
    public List<CommunityChild> childList(BaseQueryParam baseQueryParam) {
        return communityMapper.childList(baseQueryParam);
    }

    @Override
    public List<BuildingPart> buildPartOfCommunity(String communityId) {
        return buildingMapper.buildPartOfCommunity(communityId);
    }
}
