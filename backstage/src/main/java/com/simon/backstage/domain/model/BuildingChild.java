package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author fengtianying
 * @date 2019/4/11 9:56
 */
@ApiModel(value = "BuildingChild", description = "楼宇之子部分")
public class BuildingChild {

    @ApiModelProperty(value = "id", example = "1weq7s62acbd4ace9aq0ewq1x1ca9wq21a")
    private String childId;
    @ApiModelProperty(value = "楼宇id", example = "1weq7s62acbd4ace9aq0ewq1x1ca9wq21a")
    private String buildingId;
    @ApiModelProperty(value = "名称", example = "A字部分")
    private String name;
    @ApiModelProperty(value = "数量", example = "50")
    private Integer amount;
    @ApiModelProperty(value = "面积", example = "300.32")
    private BigDecimal area;
    @ApiModelProperty(value = "用途", example = "商业")
    private String purpose;
    @ApiModelProperty(value = "千分比之相对价值", example = "1weq7s62acbd4ace9aq0ewq1x1ca9wq21a")
    private BigDecimal perthousand;

    @ApiModelProperty(value = "（无用）子部分之提示:数量/总面积/用途/千分比", example = "数量")
    private String tips;
    @ApiModelProperty(value = "（无用）子部分之值:50/125.5/停车/50", example = "50")
    private String value;
    @ApiModelProperty(value = "排序", example = "999")
    private Integer sort;

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public BigDecimal getPerthousand() {
        return perthousand;
    }

    public void setPerthousand(BigDecimal perthousand) {
        this.perthousand = perthousand;
    }
}
