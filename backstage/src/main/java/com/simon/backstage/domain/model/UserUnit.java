package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "UserUnit", description = "用户单元关系")
public class UserUnit {
	@ApiModelProperty(value="id")
    private Long userUnitId;

    @ApiModelProperty(value="住户id", example="387543cd0f6b48a9a2debae3f82b54b8")
    private String userId;

    @ApiModelProperty(value="单元id", example="ac13695d8745432993f5c5f1df8d5c54")
    private String unitId;

    @ApiModelProperty(value="是否为业主)", example="true")
    private boolean owner;

    @ApiModelProperty(value="出头的业主/首个业主/收费title使用的业主)", readOnly=true)
    private boolean convincing;

    @ApiModelProperty(value="业权分配)",example="1")
    private Integer title;
    
    @ApiModelProperty(value="创建时间", readOnly=true)
    private Date createTime;

    @ApiModelProperty(value="更新时间", readOnly=true)
    private Date updateTime;

    public Long getUserUnitId() {
        return userUnitId;
    }

    public void setUserUnitId(Long userUnitId) {
        this.userUnitId = userUnitId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public boolean getOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean getConvincing() {
        return convincing;
    }

    public void setConvincing(boolean convincing) {
        this.convincing = convincing;
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

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "UserUnit{" +
                "userUnitId=" + userUnitId +
                ", userId='" + userId + '\'' +
                ", unitId='" + unitId + '\'' +
                ", owner=" + owner +
                ", convincing=" + convincing +
                '}';
    }
}