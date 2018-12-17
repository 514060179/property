package com.simon.dal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
@ApiModel(value = "PlaceRecord", description = "订场记录")
public class PlaceRecord {
    @ApiModelProperty(name="id",readOnly=true)
    private String recordId;

    @ApiModelProperty(name="用户id",readOnly=true)
    private String userId;

    @ApiModelProperty("场所id")
    private String placeId;

    @ApiModelProperty(name="预订日期",example="2018-08-08")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date orderDate;

    @ApiModelProperty(name="开始时间",example="2018-08-08 08:08:08")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date orderStartTime;

    @ApiModelProperty(name="结束时间",example="2018-08-08 08:08:08")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date orderEndTime;

    @ApiModelProperty(name="预定状态(-1预约取消0开始发起1预约成功2预约失败)",readOnly=true)
    private String recordStatus;

    @ApiModelProperty(name="创建时间",readOnly=true)
    private Date createTime;

    @ApiModelProperty(name="更新时间",readOnly=true)
    private Date updateTime;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId == null ? null : placeId.trim();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Date orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus == null ? null : recordStatus.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}