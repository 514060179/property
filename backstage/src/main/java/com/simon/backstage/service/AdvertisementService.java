package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Advertisement;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;

/**
 * @author fengtianying
 * @date 2019/1/25 14:07
 */
public interface AdvertisementService {

    /**
     * 新增
     * @param advertisement
     * @return
     */
    Advertisement add(Advertisement advertisement);

    /**
     * 详情
     * @param advertisementId
     * @return
     */
    Advertisement detail(String advertisementId);

    /**
     * 修改
     * @param advertisement
     * @return
     */
    int upd(Advertisement advertisement);

    /**
     * 删除
     * @param advertisementId
     * @return
     */
    int del(String advertisementId);

    /**
     * 列表
     * @param baseQueryParam
     * @return
     */
    PageInfo<Advertisement> list(BaseQueryParam baseQueryParam);

    /**
     * 获取触摸板广告
     * @param communityId
     * @param buildingId
     * @return
     */
    List<Advertisement> touchLlist(String communityId,String buildingId);

    /**
     * 发布广告
     * @param advertisementId
     * @param used
     * @return
     */
    int publish(String advertisementId, boolean used);
}
