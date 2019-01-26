package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "AdvanceMoney", description = "预付账户")
public class UnitChargeItem {
    private String unitItemId;
    @ApiModelProperty(value = "项目id",example = "23722e5355b741234882qwee4fade372")
    private String itemId;
    @ApiModelProperty(value = "单元id",example = "0e722e5735b421d45826b5234qsde312")
    private String unitId;
    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private Date updateTime;

    public String getUnitItemId() {
        return unitItemId;
    }

    public void setUnitItemId(String unitItemId) {
        this.unitItemId = unitItemId == null ? null : unitItemId.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}