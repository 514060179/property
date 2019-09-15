package com.simon.backstage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author simon feng
 * @date 2019-09-10 23:28
 * @description excel导出单元model
 */
@ApiModel(value = "ExcelUnit", description = "excel导出单元model")
public class ExcelUnit {

    @ApiModelProperty(value = "社区名称",example = "潭州社区")
    private String communityName;

    @ApiModelProperty(value = "单元名字",example = "A001")
    private String unitName;

    @ApiModelProperty(value = "单元编号",example = "A001")
    private String unitNo;

    @ApiModelProperty(value = "单位用途1商业2轻型汽车/电单车3住宅",example = "1")
    private Integer unitType;

    @ApiModelProperty(value = "单元状态0空置1租赁2装修中3入住",example = "3")
    private Integer unitStatus;

    @ApiModelProperty(value = "全址",example = "三楼A001")
    private String unitFullAddress;

    @ApiModelProperty(value = "用途",example = "居住")
    private String unitPurpose;

    @ApiModelProperty(value = "位置(地下,一楼,二楼,三楼,户外.)",example = "三楼")
    private String unitPosition;

    @ApiModelProperty(value = "分层建筑物相对比(千分之一)",example = "0.002")
    private BigDecimal unitRelativeProportion;

    @ApiModelProperty(value = "分层建筑物之子部分相对比(千分之一)",example = "0.05")
    private BigDecimal unitChildRelativeProportion;

    @ApiModelProperty(value = "单位业权",example = "10")
    private Integer unitTitle;

    @ApiModelProperty(value = "住户/业主",example = "simon/fengtianying")
    private String users;

    @ApiModelProperty(value = "建筑名称",example = "一栋")
    private String buildingName;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public Integer getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(Integer unitStatus) {
        this.unitStatus = unitStatus;
    }

    public String getUnitFullAddress() {
        return unitFullAddress;
    }

    public void setUnitFullAddress(String unitFullAddress) {
        this.unitFullAddress = unitFullAddress;
    }

    public String getUnitPurpose() {
        return unitPurpose;
    }

    public void setUnitPurpose(String unitPurpose) {
        this.unitPurpose = unitPurpose;
    }

    public String getUnitPosition() {
        return unitPosition;
    }

    public void setUnitPosition(String unitPosition) {
        this.unitPosition = unitPosition;
    }

    public BigDecimal getUnitRelativeProportion() {
        return unitRelativeProportion;
    }

    public void setUnitRelativeProportion(BigDecimal unitRelativeProportion) {
        this.unitRelativeProportion = unitRelativeProportion;
    }

    public BigDecimal getUnitChildRelativeProportion() {
        return unitChildRelativeProportion;
    }

    public void setUnitChildRelativeProportion(BigDecimal unitChildRelativeProportion) {
        this.unitChildRelativeProportion = unitChildRelativeProportion;
    }

    public Integer getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(Integer unitTitle) {
        this.unitTitle = unitTitle;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
