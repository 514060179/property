package com.simon.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simon.app.service.VersionService;
import com.simon.dal.dao.VersionMapper;
import com.simon.dal.model.Version;

@Service
public class VersionServiceImpl implements VersionService {

	@Autowired
	private VersionMapper versionMapper;
	
	@Override
	public Version findVersion(Version record) {
		Version version = versionMapper.findVersion(record);
		if(version != null){
			return version;
		}
		return null;
	}
}
