package com.simon.app.service;

import com.simon.dal.model.Version;

public interface VersionService {
	/**
	 * 查询版本信息
	 * @param record
	 * @return
	 */
	Version findVersion(Version record);
}
