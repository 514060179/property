package com.simon.dal.dao;

import java.util.List;

import com.simon.dal.model.Images;

public interface ImageMapper {
	
    int insertSelective(Images record);

	int insertBatch(List<Images> list);
}
