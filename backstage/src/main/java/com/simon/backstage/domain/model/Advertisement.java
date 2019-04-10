package com.simon.backstage.domain.model;

import com.simon.backstage.domain.vo.Community;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "Advertisement", description = "广告")
public class Advertisement implements Cloneable {

    @ApiModelProperty(value = "id",example = "1ewq23wqe1dvhjkjk2sdf31ewqe23eeq")
    private String advId;

    @ApiModelProperty(value = "社区id",example = "05c63989a7344d10940410115aac6214")
    private String communityId;

    @ApiModelProperty(value = "社区id集合")
    private List<String> communityIds;

    @ApiModelProperty(value = "楼宇id",example = "8caf2c8574b640a69044e668526a9ae8")
    private String buildingId;

    @ApiModelProperty(value = "楼宇id集合")
    private List<String> buildingIds;

    @ApiModelProperty(value = "标题",example = "")
    private String title;

    @ApiModelProperty(value = "广告类型0普通图片1视频",example = "0")
    private Integer type;

    @ApiModelProperty(value = "资源utl",example = "/simon/test/image")
    private String url;

    @ApiModelProperty(value = "播放时间（单位秒）",example = "10")
    private Integer residenceTime;

    @ApiModelProperty(value = "播放时间（单位秒）",example = "10")
    private Integer purpose;

    @ApiModelProperty(value = "描述",example = "这是普通广告")
    private String describe;

    @ApiModelProperty(hidden = true)
    private Date createTime;

    @ApiModelProperty(hidden = true)
    private Date updateTime;

    @ApiModelProperty(value = "开始日期",example = "2018-11-11")
    private Date startTime;

    @ApiModelProperty(value = "结束日期",example = "2019-11-11")
    private Date endTime;

    @ApiModelProperty(value = "是否使用0否1是",example = "0")
    private Boolean used;
    @ApiModelProperty(hidden = true)
    private Community community;
    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId == null ? null : advId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public List<String> getCommunityIds() {
        return communityIds;
    }

    public void setCommunityIds(List<String> communityIds) {
        this.communityIds = communityIds;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId == null ? null : buildingId.trim();
    }

    public List<String> getBuildingIds() {
        return buildingIds;
    }

    public void setBuildingIds(List<String> buildingIds) {
        this.buildingIds = buildingIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getResidenceTime() {
        return residenceTime;
    }

    public void setResidenceTime(Integer residenceTime) {
        this.residenceTime = residenceTime;
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

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Integer getPurpose() {
        return purpose;
    }

    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
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

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}