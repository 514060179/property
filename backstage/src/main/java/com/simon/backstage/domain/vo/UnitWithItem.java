package com.simon.backstage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/1/27 10:45
 */
@ApiModel(value = "UnitWithItem", description = "单元&收费项目")
public class UnitWithItem {

    @ApiModelProperty(value = "id",example = "21b7acqwe1514q2397083afsag278f34")
    private String unitItemId;

    @ApiModelProperty(value = "收费项目id",example = "38b7ac8b91514f5797083af4d3231cfd")
    private String itemId;

    @ApiModelProperty(value = "单元id",example = "dae8e4e7d87c416ab0636e1c4f07e87f")
    private String unitId;

    @ApiModelProperty(value = "收费项类型（不用理会）",example = "0")
    private int type;

    public String getUnitItemId() {
        return unitItemId;
    }

    public void setUnitItemId(String unitItemId) {
        this.unitItemId = unitItemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
