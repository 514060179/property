package com.simon.backstage.domain.model;

import com.simon.backstage.domain.vo.Community;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "ChargeItem", description = "收费项目")
public class ChargeItem {
    private String itemId;
    private String unitItemId;
    @ApiModelProperty(value = "社区id",example = "c123456")
    private String communityId;
    @ApiModelProperty(value = "收费项目编号",example = "A0001")
    private String itemNo;
    @ApiModelProperty(value = "收费项目名字",example = "物业费")
    private String itemName;
    @ApiModelProperty(value = "收费模式0周期性1临时性",example = "0")
    private Integer billingMode;
    @ApiModelProperty(value = "计算方式0定额1公式",example = "0")
    private Integer alculationMethod;
    @ApiModelProperty(value = "计算单价",example = "11.2")
    private BigDecimal unitPrice;
    @ApiModelProperty(value = "额外费用(基金费用)",example = "450")
    private BigDecimal additionalCost;
    @ApiModelProperty(value = "滞纳金率(单位:百分之一)",example = "20")
    private Integer lateFee;
    @ApiModelProperty(value = "滞纳开始天数",example = "20")
    private Integer lateDate;
    @ApiModelProperty(value = "计算公式",example = "11.2")
    private String formula;
    @ApiModelProperty(value = "金额",example = "11.2")
    private String describe;
    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private Date updateTime;
    //是否存在
    private boolean repeat;

    @ApiModelProperty(hidden = true)
    private Community community;

    private Unit unit;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getUnitItemId() {
        return unitItemId;
    }

    public void setUnitItemId(String unitItemId) {
        this.unitItemId = unitItemId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Integer getBillingMode() {
        return billingMode;
    }

    public void setBillingMode(Integer billingMode) {
        this.billingMode = billingMode;
    }

    public Integer getAlculationMethod() {
        return alculationMethod;
    }

    public void setAlculationMethod(Integer alculationMethod) {
        this.alculationMethod = alculationMethod;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(BigDecimal additionalCost) {
        this.additionalCost = additionalCost;
    }

    public Integer getLateFee() {
        return lateFee;
    }

    public void setLateFee(Integer lateFee) {
        this.lateFee = lateFee;
    }

    public Integer getLateDate() {
        return lateDate;
    }

    public void setLateDate(Integer lateDate) {
        this.lateDate = lateDate;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula == null ? null : formula.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
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

    public boolean getRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}