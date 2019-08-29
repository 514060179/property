package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author simon feng
 * @date 2019-08-29 12:47
 * @description 建筑与子部分
 */
public class BuildingPart {

    @ApiModelProperty("id")
    private String buildingId;

    @ApiModelProperty(value = "楼宇名字",example = "生产楼")
    private String buildingName;

    @ApiModelProperty(value = "楼宇编号",example = "TC001")
    private String buildingNo;


    List<BuildingChild> buildingChildList;

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public List<BuildingChild> getBuildingChildList() {
        return buildingChildList;
    }

    public void setBuildingChildList(List<BuildingChild> buildingChildList) {
        this.buildingChildList = buildingChildList;
    }
}
