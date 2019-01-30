package com.simon.backstage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author fengtianying
 * @date 2019/1/28 10:00
 */
@ApiModel(value = "CommunityWithBuilding", description = "社区(楼宇)")
public class CommunityWithBuilding {

    @ApiModelProperty(value="社区id",readOnly=true)
    private String communityId;

    @ApiModelProperty("社区编号")
    private String communityNo;

    @ApiModelProperty("社区名字")
    private String communityName;

    @ApiModelProperty("管理类型(0普通管理1综合管理)")
    private String communityManagementType;

    private List<BuildingWithUnit> buildingWithUnits;

    private List<Unit> unitList;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityNo() {
        return communityNo;
    }

    public void setCommunityNo(String communityNo) {
        this.communityNo = communityNo;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCommunityManagementType() {
        return communityManagementType;
    }

    public void setCommunityManagementType(String communityManagementType) {
        this.communityManagementType = communityManagementType;
    }

    public List<BuildingWithUnit> getBuildingWithUnits() {
        return buildingWithUnits;
    }

    public void setBuildingWithUnits(List<BuildingWithUnit> buildingWithUnits) {
        this.buildingWithUnits = buildingWithUnits;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }
}
