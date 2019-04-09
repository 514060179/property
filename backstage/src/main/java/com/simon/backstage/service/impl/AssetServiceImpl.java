package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.AssetMapper;
import com.simon.backstage.domain.model.Asset;
import com.simon.backstage.service.AssetService;
import com.simon.dal.constant.Type;
import com.simon.dal.dao.ImageMapper;
import com.simon.dal.model.Images;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产业务层
 */
@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    @Transactional
    public Asset add(Asset asset) {
        asset.setAssetId(UUIDUtil.uidString());
        if(!asset.getAssetImage().isEmpty() && asset.getAssetImage().get(0)!=null){
            List<Images> list = new ArrayList<>();
            asset.getAssetImage().forEach(images -> {
                Images image = new Images();
                image.setImageId(UUIDUtil.uidString());
                image.setImageUrl(images.getImageUrl());
                image.setImageThumbnail(images.getImageThumbnail());
                image.setImageType(Type.IMAGE_TYPE_OTHER);
                image.setObjectId(asset.getAssetId());
                list.add(image);
            });
            imageMapper.insertBatch(list);
            asset.setAssetImage(list);
        }
        assetMapper.insertSelective(asset);
        return asset;
    }

    @Override
    public Asset detail(String assetId) {
        return assetMapper.selectByPrimaryKey(assetId);
    }

    @Override
    public int upd(Asset asset) {
        return assetMapper.updateByPrimaryKeySelective(asset);
    }

    @Override
    public int del(String assetId) {
        return assetMapper.deleteByPrimaryKey(assetId);
    }

    @Override
    public PageInfo<Asset> list(BaseClaims baseClaims) {
        PageHelper.startPage(baseClaims.getPageNo(),baseClaims.getPageSize());
        return new PageInfo<>(assetMapper.selectByCondition(baseClaims));
    }
}
