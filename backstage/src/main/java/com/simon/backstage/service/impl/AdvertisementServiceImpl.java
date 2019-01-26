package com.simon.backstage.service.impl;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.AdvertisementMapper;
import com.simon.backstage.domain.model.Advertisement;
import com.simon.backstage.service.AdvertisementService;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        int i = advertisementMapper.insertSelective(advertisement);
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
