package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/4/11 9:08
 */
@ApiModel(value = "Floor", description = "楼层")
public class Floor {

    @ApiModelProperty(value = "id", example = "1weq7862acbd4ace9a0b2a231ca903a")
    private String floorId;
    @ApiModelProperty(value = "楼宇id", example = "b0ce7862acbd4ace9a0b2a58f6ca903a")
    private String buildingId;
    @ApiModelProperty(value = "楼层名称", example = "一楼")
    private String floorName;
    @ApiModelProperty(value = "楼层用途", example = "停车场")
    private String floorPurpose;
    @ApiModelProperty(value = "楼层平面图", example = "/app/images/1552376623395.png")
    private String floorIchnography;
    @ApiModelProperty(value = "排序", example = "999")
    private Integer floorSort;

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getFloorPurpose() {
        return floorPurpose;
    }

    public void setFloorPurpose(String floorPurpose) {
        this.floorPurpose = floorPurpose;
    }

    public String getFloorIchnography() {
        return floorIchnography;
    }

    public void setFloorIchnography(String floorIchnography) {
        this.floorIchnography = floorIchnography;
    }

    public Integer getFloorSort() {
        return floorSort;
    }

    public void setFloorSort(Integer floorSort) {
        this.floorSort = floorSort;
    }
}
