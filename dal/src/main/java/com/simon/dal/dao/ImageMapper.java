package com.simon.dal.dao;

import com.simon.dal.model.Image;

public interface ImageMapper {
    int deleteByPrimaryKey(String imageId);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(String imageId);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

}
