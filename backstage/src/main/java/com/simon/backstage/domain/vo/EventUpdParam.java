package com.simon.backstage.domain.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class EventUpdParam {

    @ApiModelProperty("id")
    @NotEmpty(message = "eventId不能为空")
    private String eventId;

    @ApiModelProperty(value = "事件进度0开始1待定2完成",example = "0")
    private Integer eventStatus;

    @ApiModelProperty(value = "备注",example = "备注")
    private String eventRemark;

    @ApiModelProperty(value = "事件日期",example = "2018-11-11")
    private Date eventDate;

    @ApiModelProperty(value = "事件类型1采购2保养3其他",example = "1")
    private Integer eventType;

    @ApiModelProperty(value = "完成时间",example = "2018-11-12")
    private Date eventFinishDate;

    @ApiModelProperty(value = "事件内容",example = "事件内容")
    private String eventContent;

    @ApiModelProperty(value = "事件原因",example = "事件原因")
    private String eventCause;

    @ApiModelProperty(value = "解决方案",example = "解决方案")
    private String eventSolve;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Integer getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(Integer eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getEventRemark() {
        return eventRemark;
    }

    public void setEventRemark(String eventRemark) {
        this.eventRemark = eventRemark;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Date getEventFinishDate() {
        return eventFinishDate;
    }

    public void setEventFinishDate(Date eventFinishDate) {
        this.eventFinishDate = eventFinishDate;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public String getEventCause() {
        return eventCause;
    }

    public void setEventCause(String eventCause) {
        this.eventCause = eventCause;
    }

    public String getEventSolve() {
        return eventSolve;
    }

    public void setEventSolve(String eventSolve) {
        this.eventSolve = eventSolve;
    }
}
