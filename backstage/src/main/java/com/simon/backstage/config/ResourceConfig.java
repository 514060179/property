package com.simon.backstage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResourceConfig {
	//文件根目录
	@Value("${resource.rootPath}")
	private String rootPath;
	//场所图片路径
	@Value("${resource.imagePath.placePath}")
	private String placePath;
	//公告
	@Value("${resource.imagePath.noticePath}")
	private String noticePath;

	@Value("${resource.imagePath.advImgPath}")
	private String advPath;

	@Value("${resource.imagePath.assetImgPath}")
	private String assetImgPath;


	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getPlacePath() {
		return placePath;
	}

	public void setPlacePath(String placePath) {
		this.placePath = placePath;
	}

	public String getNoticePath() {
		return noticePath;
	}

	public void setNoticePath(String noticePath) {
		this.noticePath = noticePath;
	}

	public String getAdvPath() {
		return advPath;
	}

	public void setAdvPath(String advPath) {
		this.advPath = advPath;
	}

	public String getAssetImgPath() {
		return assetImgPath;
	}

	public void setAssetImgPath(String assetImgPath) {
		this.assetImgPath = assetImgPath;
	}
}
