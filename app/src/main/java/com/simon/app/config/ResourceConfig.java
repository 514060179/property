package com.simon.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResourceConfig {
	//文件根目录
	@Value("${resource.rootPath}")
	private String rootPath;
	
	//图片目录
	@Value("${resource.imagePath}")
	private String imagePath;
	
	//音频文件目录
	@Value("${resource.filePath}")
	private String filePath;

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
