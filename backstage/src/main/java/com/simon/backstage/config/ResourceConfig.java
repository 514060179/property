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

	@Value("${resource.imagePath.advVideoPath}")
	private String advVideoPath;

	@Value("${resource.imagePath.assetImgPath}")
	private String assetImgPath;

	@Value("${resource.imagePath.eventPath}")
	private String eventPath;

	@Value("${resource.imagePath.visitorPath}")
	private String visitorPath;

	@Value("${resource.imagePath.floorPath}")
	private String floorPath;

	@Value("${resource.imagePath.managerPath}")
	private String managerPath;

	@Value("${resource.filePath.buildingPdfPath}")
	private String buildingPdfPath;

	@Value("${resource.filePath.buildingOrderPath}")
	private String buildingOrderPath;

	@Value("${resource.filePath.eventPdfPath}")
	private String eventPdfPath;

	@Value("${resource.filePath.communityPdfPath}")
	private String communityPdfPath;



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

	public String getAdvVideoPath() {
		return advVideoPath;
	}

	public void setAdvVideoPath(String advVideoPath) {
		this.advVideoPath = advVideoPath;
	}

	public String getAssetImgPath() {
		return assetImgPath;
	}

	public void setAssetImgPath(String assetImgPath) {
		this.assetImgPath = assetImgPath;
	}

	public String getEventPath() {
		return eventPath;
	}

	public void setEventPath(String eventPath) {
		this.eventPath = eventPath;
	}

	public String getVisitorPath() {
		return visitorPath;
	}

	public void setVisitorPath(String visitorPath) {
		this.visitorPath = visitorPath;
	}

	public String getFloorPath() {
		return floorPath;
	}

	public void setFloorPath(String floorPath) {
		this.floorPath = floorPath;
	}

	public String getManagerPath() {
		return managerPath;
	}

	public void setManagerPath(String managerPath) {
		this.managerPath = managerPath;
	}

	public String getBuildingPdfPath() {
		return buildingPdfPath;
	}

	public void setBuildingPdfPath(String buildingPdfPath) {
		this.buildingPdfPath = buildingPdfPath;
	}

	public String getBuildingOrderPath() {
		return buildingOrderPath;
	}

	public void setBuildingOrderPath(String buildingOrderPath) {
		this.buildingOrderPath = buildingOrderPath;
	}

	public String getEventPdfPath() {
		return eventPdfPath;
	}

	public void setEventPdfPath(String eventPdfPath) {
		this.eventPdfPath = eventPdfPath;
	}

	public String getCommunityPdfPath() {
		return communityPdfPath;
	}

	public void setCommunityPdfPath(String communityPdfPath) {
		this.communityPdfPath = communityPdfPath;
	}
}
