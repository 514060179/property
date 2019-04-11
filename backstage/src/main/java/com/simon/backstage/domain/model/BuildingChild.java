package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
    @ApiModelProperty(value = "子部分之提示:数量/总面积/用途/千分比", example = "数量")
    private String tips;
    @ApiModelProperty(value = "子部分之值:50/125.5/停车/50", example = "50")
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
}
