package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Asset;
import com.simon.dal.vo.BaseClaims;

/**
 *
 */
public interface AssetService {

    /**
     * 新增
     * @param asset
     * @return
     */
    Asset add(Asset asset);

    /**
     * 新增
     * @param assetId
     * @return
     */
    Asset detail(String assetId);

    /**
     * 修改
     * @param asset
     * @return
     */
    int upd(Asset asset);

    /**
     * 删除
     * @param assetId
     * @return
     */
    int del(String assetId);

    /**
     * 列表
     * @param baseClaims
     * @return
     */
    PageInfo<Asset> list(BaseClaims baseClaims);
}
