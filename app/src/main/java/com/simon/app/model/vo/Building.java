package com.simon.app.model.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/2/20 16:56
 */
public class Building {

    @ApiModelProperty("id")
    private String buildingId;

    @ApiModelProperty(value = "楼宇名字",example = "生产楼")
    private String buildingName;

    @ApiModelProperty(value = "楼宇编号",example = "TC001")
    private String buildingNo;

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
}
