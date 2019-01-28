package com.simon.backstage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/1/28 10:24
 */
@ApiModel(value = "Unit", description = "单元")
public class Unit {

    @ApiModelProperty("id")
    private String unitId;

    @ApiModelProperty(value = "单元名字",example = "A001")
    private String unitName;

    @ApiModelProperty(value = "单元编号",example = "A001")
    private String unitNo;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
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
}
