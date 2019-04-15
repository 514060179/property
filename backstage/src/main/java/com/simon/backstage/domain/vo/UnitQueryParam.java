package com.simon.backstage.domain.vo;

import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UnitQueryParam", description = "单元查询条件")
public class UnitQueryParam extends BaseQueryParam {

    @ApiModelProperty(value = "单位用途1商业2轻型汽车/电单车3住宅",example = "1")
    private Integer unitType;

    @ApiModelProperty(value = "建筑id",example = "eab24245e0d0479fad34a5973644e691")
    private String buildingId;

    @ApiModelProperty(value = "業主id",example = "eab24245e0d0479fad34a5973644e691")
    private String userId;

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
