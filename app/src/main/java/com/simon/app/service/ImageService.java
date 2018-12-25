package com.simon.app.service;

import java.util.List;

import com.simon.dal.model.Image;

public interface ImageService {
	/**
	 * 新增图片
	 * @param record
	 * @return
	 */
	int insertBatch(List<Image> list);

}
