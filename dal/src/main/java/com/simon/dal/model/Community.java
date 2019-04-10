package com.simon.dal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "Community", description = "社区")
public class Community {

    @ApiModelProperty(value="社区id")
    private String communityId;

    @ApiModelProperty("物业标示编号")
    private String communityNo;

    @ApiModelProperty("社区名称")
    private String communityName;

    @ApiModelProperty("社区纬度")
    private BigDecimal communityLocX;

    @ApiModelProperty("社区经度")
    private BigDecimal communityLocY;

    @ApiModelProperty("社区全址")
    private String communityAddress;

    @ApiModelProperty(value = "地址方向1提示",example = "东北")
    private String communityAddressDirectionFirstTips;
    @ApiModelProperty(value = "地址方向1值",example = "历史大厦")
    private String communityAddressDirectionFirstValue;
    @ApiModelProperty(value = "地址方向2提示",example = "东南")
    private String communityAddressDirectionSecondTips;
    @ApiModelProperty(value = "地址方向2值",example = "华发广场")
    private String communityAddressDirectionSecondValue;
    @ApiModelProperty(value = "地址方向3提示",example = "西北")
    private String communityAddressDirectionThirdTips;
    @ApiModelProperty(value = "地址方向3值",example = "太川科技")
    private String communityAddressDirectionThirdValue;
    @ApiModelProperty(value = "地址方向4提示",example = "西南")
    private String communityAddressDirectionFourthTips;
    @ApiModelProperty(value = "地址方向4值",example = "华润万家")
    private String communityAddressDirectionFourthValue;

    @ApiModelProperty("总面积(平方米)")
    private BigDecimal communityArea;

    @ApiModelProperty("覆盖面积(平方米)")
    private BigDecimal communityCoverArea;
    @ApiModelProperty("无覆盖面积(平方米)")
    private BigDecimal communityNoCoverArea;
    @ApiModelProperty("垂直占用空间面积(平方米)")
    private BigDecimal communityVerticalArea;
    @ApiModelProperty("商铺面积(平方米)")
    private BigDecimal communityShopsArea;
    @ApiModelProperty("住宅面积(平方米)")
    private BigDecimal communityHouseArea;

    @ApiModelProperty("建筑面积")
    private BigDecimal communityBuildingArea;

    @ApiModelProperty("道路面积")
    private BigDecimal communityRoadArea;

    @ApiModelProperty("绿化面积")
    private BigDecimal communityGreenarea;

    @ApiModelProperty("公用面积")
    private BigDecimal communityCommonArea;

    @ApiModelProperty("车库面积")
    private BigDecimal communityGarageArea;

    @ApiModelProperty("车位数量")
    private Integer communityGarageCount;

    @ApiModelProperty("住宅总数")
    private Integer communityRoomCount;

    @ApiModelProperty("商铺数量")
    private Integer communityShopsCount;

    @ApiModelProperty("联系人")
    private String communityContacts;

    @ApiModelProperty("联系人号码")
    private String communityContactsPhone;

    @ApiModelProperty("备注")
    private String communityRemark;

    @ApiModelProperty(value="是否删除",readOnly=true)
    private Boolean communityDeleted;

    @ApiModelProperty("管理类型(0简单管理1综合管理)")
    private String communityManagementType;

    @ApiModelProperty(value="创建时间",readOnly=true)
    private Date createTime;

    @ApiModelProperty(value="更新时间",readOnly=true)
    private Date updateTime;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public String getCommunityNo() {
        return communityNo;
    }

    public void setCommunityNo(String communityNo) {
        this.communityNo = communityNo == null ? null : communityNo.trim();
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName == null ? null : communityName.trim();
    }

    public BigDecimal getCommunityLocX() {
        return communityLocX;
    }

    public void setCommunityLocX(BigDecimal communityLocX) {
        this.communityLocX = communityLocX;
    }

    public BigDecimal getCommunityLocY() {
        return communityLocY;
    }

    public void setCommunityLocY(BigDecimal communityLocY) {
        this.communityLocY = communityLocY;
    }

    public String getCommunityAddress() {
        return communityAddress;
    }

    public void setCommunityAddress(String communityAddress) {
        this.communityAddress = communityAddress == null ? null : communityAddress.trim();
    }

    public BigDecimal getCommunityArea() {
        return communityArea;
    }

    public void setCommunityArea(BigDecimal communityArea) {
        this.communityArea = communityArea;
    }

    public BigDecimal getCommunityBuildingArea() {
        return communityBuildingArea;
    }

    public void setCommunityBuildingArea(BigDecimal communityBuildingArea) {
        this.communityBuildingArea = communityBuildingArea;
    }

    public BigDecimal getCommunityRoadArea() {
        return communityRoadArea;
    }

    public void setCommunityRoadArea(BigDecimal communityRoadArea) {
        this.communityRoadArea = communityRoadArea;
    }

    public BigDecimal getCommunityGreenarea() {
        return communityGreenarea;
    }

    public void setCommunityGreenarea(BigDecimal communityGreenarea) {
        this.communityGreenarea = communityGreenarea;
    }

    public BigDecimal getCommunityCommonArea() {
        return communityCommonArea;
    }

    public void setCommunityCommonArea(BigDecimal communityCommonArea) {
        this.communityCommonArea = communityCommonArea;
    }

    public BigDecimal getCommunityGarageArea() {
        return communityGarageArea;
    }

    public void setCommunityGarageArea(BigDecimal communityGarageArea) {
        this.communityGarageArea = communityGarageArea;
    }

    public Integer getCommunityGarageCount() {
        return communityGarageCount;
    }

    public void setCommunityGarageCount(Integer communityGarageCount) {
        this.communityGarageCount = communityGarageCount;
    }

    public Integer getCommunityRoomCount() {
        return communityRoomCount;
    }

    public void setCommunityRoomCount(Integer communityRoomCount) {
        this.communityRoomCount = communityRoomCount;
    }

    public String getCommunityContacts() {
        return communityContacts;
    }

    public void setCommunityContacts(String communityContacts) {
        this.communityContacts = communityContacts;
    }

    public String getCommunityContactsPhone() {
        return communityContactsPhone;
    }

    public void setCommunityContactsPhone(String communityContactsPhone) {
        this.communityContactsPhone = communityContactsPhone;
    }

    public String getCommunityRemark() {
        return communityRemark;
    }

    public void setCommunityRemark(String communityRemark) {
        this.communityRemark = communityRemark;
    }

    public Boolean getCommunityDeleted() {
        return communityDeleted;
    }

    public void setCommunityDeleted(Boolean communityDeleted) {
        this.communityDeleted = communityDeleted;
    }

    public String getCommunityManagementType() {
        return communityManagementType;
    }

    public void setCommunityManagementType(String communityManagementType) {
        this.communityManagementType = communityManagementType == null ? null : communityManagementType.trim();
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

    public String getCommunityAddressDirectionFirstTips() {
        return communityAddressDirectionFirstTips;
    }

    public void setCommunityAddressDirectionFirstTips(String communityAddressDirectionFirstTips) {
        this.communityAddressDirectionFirstTips = communityAddressDirectionFirstTips;
    }

    public String getCommunityAddressDirectionFirstValue() {
        return communityAddressDirectionFirstValue;
    }

    public void setCommunityAddressDirectionFirstValue(String communityAddressDirectionFirstValue) {
        this.communityAddressDirectionFirstValue = communityAddressDirectionFirstValue;
    }

    public String getCommunityAddressDirectionSecondTips() {
        return communityAddressDirectionSecondTips;
    }

    public void setCommunityAddressDirectionSecondTips(String communityAddressDirectionSecondTips) {
        this.communityAddressDirectionSecondTips = communityAddressDirectionSecondTips;
    }

    public String getCommunityAddressDirectionSecondValue() {
        return communityAddressDirectionSecondValue;
    }

    public void setCommunityAddressDirectionSecondValue(String communityAddressDirectionSecondValue) {
        this.communityAddressDirectionSecondValue = communityAddressDirectionSecondValue;
    }

    public String getCommunityAddressDirectionThirdTips() {
        return communityAddressDirectionThirdTips;
    }

    public void setCommunityAddressDirectionThirdTips(String communityAddressDirectionThirdTips) {
        this.communityAddressDirectionThirdTips = communityAddressDirectionThirdTips;
    }

    public String getCommunityAddressDirectionThirdValue() {
        return communityAddressDirectionThirdValue;
    }

    public void setCommunityAddressDirectionThirdValue(String communityAddressDirectionThirdValue) {
        this.communityAddressDirectionThirdValue = communityAddressDirectionThirdValue;
    }

    public String getCommunityAddressDirectionFourthTips() {
        return communityAddressDirectionFourthTips;
    }

    public void setCommunityAddressDirectionFourthTips(String communityAddressDirectionFourthTips) {
        this.communityAddressDirectionFourthTips = communityAddressDirectionFourthTips;
    }

    public String getCommunityAddressDirectionFourthValue() {
        return communityAddressDirectionFourthValue;
    }

    public void setCommunityAddressDirectionFourthValue(String communityAddressDirectionFourthValue) {
        this.communityAddressDirectionFourthValue = communityAddressDirectionFourthValue;
    }

    public BigDecimal getCommunityCoverArea() {
        return communityCoverArea;
    }

    public void setCommunityCoverArea(BigDecimal communityCoverArea) {
        this.communityCoverArea = communityCoverArea;
    }

    public BigDecimal getCommunityNoCoverArea() {
        return communityNoCoverArea;
    }

    public void setCommunityNoCoverArea(BigDecimal communityNoCoverArea) {
        this.communityNoCoverArea = communityNoCoverArea;
    }

    public BigDecimal getCommunityVerticalArea() {
        return communityVerticalArea;
    }

    public void setCommunityVerticalArea(BigDecimal communityVerticalArea) {
        this.communityVerticalArea = communityVerticalArea;
    }

    public BigDecimal getCommunityShopsArea() {
        return communityShopsArea;
    }

    public void setCommunityShopsArea(BigDecimal communityShopsArea) {
        this.communityShopsArea = communityShopsArea;
    }

    public BigDecimal getCommunityHouseArea() {
        return communityHouseArea;
    }

    public void setCommunityHouseArea(BigDecimal communityHouseArea) {
        this.communityHouseArea = communityHouseArea;
    }

    public Integer getCommunityShopsCount() {
        return communityShopsCount;
    }

    public void setCommunityShopsCount(Integer communityShopsCount) {
        this.communityShopsCount = communityShopsCount;
    }
}