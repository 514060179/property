package com.simon.backstage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author fengtianying
 * @date 2019/1/28 10:02
 */
@ApiModel(value = "BuildingWithUnit", description = "楼宇(单元)")
public class BuildingWithUnit {

    @ApiModelProperty("id")
    private String buildingId;

    @ApiModelProperty(value = "楼宇名字",example = "生产楼")
    private String buildingName;

    @ApiModelProperty(value = "楼宇编号",example = "TC001")
    private String buildingNo;

    @ApiModelProperty(value = "楼宇全址",example = "珠海太川110号")
    private String fullAddress;

    private List<Unit> unitList;

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

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }
}
