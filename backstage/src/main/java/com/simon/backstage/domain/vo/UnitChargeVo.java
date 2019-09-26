package com.simon.backstage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author simon feng
 * @date 2019-09-24 19:56
 * @description 物业收费vo
 */
@ApiModel(value = "UnitChargeVo", description = "物业收费表格vo")
public class UnitChargeVo {

    @ApiModelProperty(value="id,用于修改v1Date,v2Money时传")
    private String id;

    @ApiModelProperty(value="x轴-日期")
    private String xDate;

    @ApiModelProperty(value="y轴-单元")
    private String yUnit;

    @ApiModelProperty(value="值1-缴费日期")
    private String v1Date;

    @ApiModelProperty(value="值2-缴费总额")
    private String v2Money;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getxDate() {
        return xDate;
    }

    public void setxDate(String xDate) {
        this.xDate = xDate;
    }

    public String getyUnit() {
        return yUnit;
    }

    public void setyUnit(String yUnit) {
        this.yUnit = yUnit;
    }

    public String getV1Date() {
        return v1Date;
    }

    public void setV1Date(String v1Date) {
        this.v1Date = v1Date;
    }

    public String getV2Money() {
        return v2Money;
    }

    public void setV2Money(String v2Money) {
        this.v2Money = v2Money;
    }
}
