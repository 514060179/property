package com.simon.backstage.domain.vo;

import com.simon.backstage.domain.model.AdvanceMoney;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author fengtianying
 * @date 2019/2/21 15:07
 */
public class ExcelUser {

    @ApiModelProperty(value="用户id")
    private String userId;

    @ApiModelProperty(value="社区id", example="c123456")
    @NotEmpty(message="社区id不能为空")
    private String communityId;

    @ApiModelProperty(value = "名字",example = "西蒙")
    private String name;

    @ApiModelProperty(value = "英文名字",example = "simon")
    private String englishName;

    @ApiModelProperty(value = "性别(0女1男)",example = "0")
    private Integer sex;

    @ApiModelProperty(value = "区号",example = "86")
    private String countryCode;

    @ApiModelProperty(value = "手机号",example = "13421266955")
    private String tel;

    private BigDecimal advanceAmount;

    private List<ExcelCharge> excelChargeList;

    private Community community;

    private AdvanceMoney advanceMoney;

    private Unit unit;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<ExcelCharge> getExcelChargeList() {
        return excelChargeList;
    }

    public BigDecimal getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(BigDecimal advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public void setExcelChargeList(List<ExcelCharge> excelChargeList) {
        this.excelChargeList = excelChargeList;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public AdvanceMoney getAdvanceMoney() {
        return advanceMoney;
    }

    public void setAdvanceMoney(AdvanceMoney advanceMoney) {
        this.advanceMoney = advanceMoney;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
