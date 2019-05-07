package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.service.PlaceService;
import com.simon.dal.constant.Type;
import com.simon.dal.dao.ImageMapper;
import com.simon.dal.dao.PlaceMapper;
import com.simon.dal.model.Images;
import com.simon.dal.model.Place;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceMapper placeMapper;

    @Autowired
    private ImageMapper imageMapper;
    @Transactional
    @Override
    public Place add(Place place) {
        place.setPlaceId(UUIDUtil.uidString());
        int i = placeMapper.insertSelective(place);
        //存在图片
        List<Images> list = new ArrayList<>();
        if (place.getImages() != null && place.getImages().size() > 0) {
            place.getImages().forEach(images -> {
                Images image = new Images();
                image.setImageId(UUIDUtil.uidString());
                image.setObjectId(place.getPlaceId());
                image.setImageUrl(images.getImageUrl());
                image.setImageThumbnail(images.getImageThumbnail());
                image.setImageType(Type.IMAGE_TYPE_PLACE);
                list.add(image);
            });
            imageMapper.insertBatch(list);
        }
        if (i>0){
            place.setImages(list);
            return place;
        }else{
            return null;
        }
    }

    @Override
    public Place detail(String placeId) {
        return placeMapper.selectByPrimaryKey(placeId);
    }

    @Override
    @Transactional
    public int upd(Place place) {
        if (place.getImages() != null && place.getImages().size() > 0) {
            List<Images> list = new ArrayList<>();
            imageMapper.deleteByObjectIdAndType(place.getPlaceId(), Type.IMAGE_TYPE_PLACE);
            place.getImages().forEach(images -> {
                Images image = new Images();
                image.setImageId(UUIDUtil.uidString());
                image.setObjectId(place.getPlaceId());
                image.setImageUrl(images.getImageUrl());
                image.setImageThumbnail(images.getImageThumbnail());
                image.setImageType(Type.IMAGE_TYPE_PLACE);
                list.add(image);
            });
            imageMapper.insertBatch(list);
        }
        return placeMapper.updateByPrimaryKeySelective(place);
    }

    @Override
    public int del(String placeId) {
        return placeMapper.deleteByPrimaryKey(placeId);
    }

    @Override
    public PageInfo<Place> list(BaseClaims baseClaims) {
        PageHelper.startPage(baseClaims.getPageNo(),baseClaims.getPageSize());
        return new PageInfo<>(placeMapper.list(baseClaims));
    }
}
