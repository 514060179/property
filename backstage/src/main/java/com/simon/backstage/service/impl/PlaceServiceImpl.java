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
        if (!place.getImages().isEmpty() && place.getImages().get(0)!=null){
        	Images images = place.getImages().get(0);
            List<Images> list = new ArrayList<>();
            List<String> imagesList = Arrays.asList(images.getImageUrl().split(","));
            List<String> thumbnailList = Arrays.asList(images.getImageThumbnail().split(","));
            for (int j = 0 ; j<imagesList.size();j++){
                Images image = new Images();
                image.setImageId(UUIDUtil.uidString());
                image.setObjectId(place.getPlaceId());
                image.setImageUrl(imagesList.get(j));
                image.setImageThumbnail(thumbnailList.get(j));
                image.setImageType(Type.IMAGE_TYPE_PLACE);
                list.add(image);
            }
            imageMapper.insertBatch(list);
            place.setImages(list);
        }
        if (i>0){
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
    public int upd(Place place) {
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
