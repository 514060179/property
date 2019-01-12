package com.simon.dal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
@ApiModel(value = "PlaceRecord", description = "订场记录")
public class PlaceRecord {
    @ApiModelProperty(value="id",readOnly=true)
    private String recordId;

    @ApiModelProperty(value="用户id",readOnly=true)
    private String userId;

    @ApiModelProperty("场所id")
    private String placeId;

    @ApiModelProperty(value="预订日期",example="2018-08-08 00:00:00")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date orderDate;

    @ApiModelProperty(value="开始时间",example="08:00:00")
    
    @JsonFormat(pattern="hh:mm:ss",timezone="GMT+8")
    private Date orderStartTime;

    @ApiModelProperty(value="结束时间",example="18:00:00")
    @JsonFormat(pattern="hh:mm:ss",timezone="GMT+8")
    private Date orderEndTime;

    @ApiModelProperty(value="预定状态(-1预约取消0开始发起1预约成功2预约失败)",readOnly=true)
    private String recordStatus;

    @ApiModelProperty(value="创建时间",readOnly=true)
    private Date createTime;

    @ApiModelProperty(value="更新时间",readOnly=true)
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