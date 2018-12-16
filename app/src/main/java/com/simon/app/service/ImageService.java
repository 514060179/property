package com.simon.app.service;

import com.simon.dal.model.Image;

public interface ImageService {
	/**
	 * 新增图片
	 * @param record
	 * @return
	 */
    int insertSelective(Image record);

}
