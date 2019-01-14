package com.simon.app.service;

import java.util.List;

import com.simon.dal.model.Images;

public interface ImageService {
	/**
	 * 新增图片
	 * @param list
	 * @return
	 */
	int insertBatch(List<Images> list);

}
