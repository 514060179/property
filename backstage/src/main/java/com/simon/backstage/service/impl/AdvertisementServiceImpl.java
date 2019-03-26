package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.AdvertisementMapper;
import com.simon.backstage.domain.model.Advertisement;
import com.simon.backstage.service.AdvertisementService;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author fengtianying
 * @date 2019/1/25 14:11
 */
@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementMapper advertisementMapper;
    @Override
    public Advertisement add(Advertisement advertisement) {
        advertisement.setAdvId(UUIDUtil.uidString());
        List<Advertisement> advertisements = new ArrayList<>();
        if (advertisement.getCommunityIds()==null){
            if (!Objects.isNull(advertisement.getBuildingIds())){
                advertisement.getBuildingIds().forEach((buildingId)->{
                    Advertisement copy = (Advertisement)advertisement.clone();
                    copy.setAdvId(UUIDUtil.uidString());
                    copy.setBuildingId(buildingId);
                    advertisements.add(copy);
                });
            }
        }else{
            advertisement.getCommunityIds().forEach((communityId)->{
                Advertisement copy = (Advertisement)advertisement.clone();
                copy.setAdvId(UUIDUtil.uidString());
                copy.setCommunityId(communityId);
                copy.setBuildingId(null);
                advertisements.add(copy);
            });
        }
        if (advertisements.size()==0){
            advertisements.add(advertisement);
        }
        int i = advertisementMapper.insertBatch(advertisements);
        if (i>0){
            return advertisement;
        }
        return null;
    }

    @Override
    public Advertisement detail(String advertisementId) {
        return advertisementMapper.selectByPrimaryKey(advertisementId);
    }

    @Override
    public int upd(Advertisement advertisement) {
        return advertisementMapper.updateByPrimaryKeySelective(advertisement);
    }

    @Override
    public int del(String advertisementId) {
        return advertisementMapper.deleteByPrimaryKey(advertisementId);
    }

    @Override
    public PageInfo<Advertisement> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(advertisementMapper.selectByConditon(baseQueryParam));
    }

    @Override
    public List<Advertisement> touchLlist(String communityId, String buildingId) {
        return advertisementMapper.selectUsable(communityId,buildingId);
    }

    @Override
    public int publish(String advertisementId, boolean used) {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvId(advertisementId);
        advertisement.setUsed(used);
        return advertisementMapper.updateByPrimaryKeySelective(advertisement);
    }
}
