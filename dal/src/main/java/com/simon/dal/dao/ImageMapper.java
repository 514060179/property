package com.simon.dal.dao;

import java.util.List;

import com.simon.dal.model.Images;
import org.apache.ibatis.annotations.Param;

public interface ImageMapper {
	
    int insertSelective(Images record);

	int insertBatch(List<Images> list);

	List<Images> findListById(@Param("id") String id, @Param("type")int type);
}
