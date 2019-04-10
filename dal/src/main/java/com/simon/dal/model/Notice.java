package com.simon.dal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "Notice", description = "广告/公告/推送")
public class Notice {
    @ApiModelProperty(value="id")
    private String noticeId;

    @ApiModelProperty(value="社区id", example="c123456")
    private String communityId;

    @ApiModelProperty(value="建筑id", example="b123456")
    private String buildingId;

    @ApiModelProperty(value="建筑名字", example="XX大厦")
    private String buildingName;

    @ApiModelProperty(value="公告类型0通告1节日提醒2注意事项3政府文件4外判公司须知5工程6办理手续", example="0")
    private Integer noticeType;

    @ApiModelProperty(value="场所标题(简体)", example="关于垃圾处理问题")
    private String noticeTitle;

    @ApiModelProperty(value="场所标题(繁体)", example="關於垃圾處理問題")
    private String noticeTraditionalTitle;

    @ApiModelProperty(value="场所标题(英文)", example="About garbage disposal")
    private String noticeEnglishTitle;

    private List<Images> noticeImage;

    @ApiModelProperty(value="创建时间",readOnly=true)
    private Date createTime;

    @ApiModelProperty(value="更新时间",readOnly=true)
    private Date updateTime;

    @ApiModelProperty(value = "开始日期",example = "2018-11-11")
    private Date startTime;

    @ApiModelProperty(value = "结束日期",example = "2019-11-11")
    private Date endTime;

    @ApiModelProperty(value="详情", example="为了培养大家的环保意识，现物业主张各位业主/住户分类垃圾......")
    private String noticeDetails;

    @ApiModelProperty(value="详情（繁体）", example="為了培養大家的環保意識，現物業主張各位業主/住戶分類垃圾......")
    private String noticeTraditionalDetails;

    @ApiModelProperty(value="详情（英文）", example="In order to cultivate everyone's awareness of environmental protection, the property now advocates that owners/households classify garbage...")
    private String noticeEnglishDetails;

    @ApiModelProperty(hidden = true)
    private Community community;

    public String getNoticeDetails() {
        return noticeDetails;
    }

    public void setNoticeDetails(String noticeDetails) {
        this.noticeDetails = noticeDetails == null ? null : noticeDetails.trim();
    }

    public String getNoticeTraditionalDetails() {
        return noticeTraditionalDetails;
    }

    public void setNoticeTraditionalDetails(String noticeTraditionalDetails) {
        this.noticeTraditionalDetails = noticeTraditionalDetails == null ? null : noticeTraditionalDetails.trim();
    }

    public String getNoticeEnglishDetails() {
        return noticeEnglishDetails;
    }

    public void setNoticeEnglishDetails(String noticeEnglishDetails) {
        this.noticeEnglishDetails = noticeEnglishDetails == null ? null : noticeEnglishDetails.trim();
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId == null ? null : noticeId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }

    public String getNoticeTraditionalTitle() {
        return noticeTraditionalTitle;
    }

    public void setNoticeTraditionalTitle(String noticeTraditionalTitle) {
        this.noticeTraditionalTitle = noticeTraditionalTitle == null ? null : noticeTraditionalTitle.trim();
    }

    public String getNoticeEnglishTitle() {
        return noticeEnglishTitle;
    }

    public void setNoticeEnglishTitle(String noticeEnglishTitle) {
        this.noticeEnglishTitle = noticeEnglishTitle == null ? null : noticeEnglishTitle.trim();
    }

    public List<Images> getNoticeImage() {
		return noticeImage;
	}

	public void setNoticeImage(List<Images> noticeImage) {
		this.noticeImage = noticeImage;
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

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}