package com.simon.dal.dao;

import java.util.List;

import com.simon.dal.model.Image;

public interface ImageMapper {
	
    int insertSelective(Image record);

	int insertBatch(List<Image> list);
}
