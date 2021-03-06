package com.simon.backstage.domain.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

public class EventUpdParam {

    @ApiModelProperty("id")
    @NotEmpty(message = "eventId不能为空")
    private String eventId;

    @ApiModelProperty(value = "社区id",example = "12312312")
    private String communityId;

    @ApiModelProperty(value = "事件进度0开始1待定2完成",example = "0")
    private String eventStatus;

    @ApiModelProperty(value = "备注",example = "备注")
    private String eventRemark;

    @ApiModelProperty(value = "事件日期",example = "2018-11-11")
    private Date eventDate;

    @ApiModelProperty(value = "事件进度0跟进中1报价中2接获投诉3与管理机关讨论中4待定5工程进行中6待开大会表决7完成",example = "1")
    private String eventType;

    @ApiModelProperty(value = "完成时间",example = "2018-11-12")
    private Date eventFinishDate;

    @ApiModelProperty(value = "事件内容",example = "事件内容")
    private String eventContent;

    @ApiModelProperty(value = "事件原因",example = "事件原因")
    private String eventCause;

    @ApiModelProperty(value = "解决方案",example = "解决方案")
    private String eventSolve;

    @ApiModelProperty(value = "资产编号",example = "ZC001")
    private String assetNo;
    @ApiModelProperty(value = "业主id",example = "7194fd60598740ed878b89aff67738f4")
    private String userId;
    @ApiModelProperty(value = "投诉位置",example = "界涌广场")
    private String complainPosition;
    @ApiModelProperty(value = "具体位置",example = "广东珠海界涌广场")
    private String complainSpecificPosition;
    @ApiModelProperty(value = "投诉类型",example = "设备更换")
    private String complainType;
    @ApiModelProperty(value = "投诉标题",example = "报修")
    private String complainClassType;
    @ApiModelProperty(value = "投诉声音",example = "/app/videos/1552818817017.amr")
    private String complainVoice;
    @ApiModelProperty(value = "投诉进度回复",example = "现在正在处理中,预计5个工作日可以完成")
    private String complainReply;

    @ApiModelProperty(value = "接收渠道0公司1app2业主口头投诉",example = "0")
    private Integer eventChannel;

    @ApiModelProperty(value = "事件报告文件")
    private List<String> reports;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
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

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComplainPosition() {
        return complainPosition;
    }

    public void setComplainPosition(String complainPosition) {
        this.complainPosition = complainPosition;
    }

    public String getComplainSpecificPosition() {
        return complainSpecificPosition;
    }

    public void setComplainSpecificPosition(String complainSpecificPosition) {
        this.complainSpecificPosition = complainSpecificPosition;
    }

    public String getComplainType() {
        return complainType;
    }

    public void setComplainType(String complainType) {
        this.complainType = complainType;
    }

    public String getComplainClassType() {
        return complainClassType;
    }

    public void setComplainClassType(String complainClassType) {
        this.complainClassType = complainClassType;
    }

    public String getComplainVoice() {
        return complainVoice;
    }

    public void setComplainVoice(String complainVoice) {
        this.complainVoice = complainVoice;
    }

    public String getComplainReply() {
        return complainReply;
    }

    public void setComplainReply(String complainReply) {
        this.complainReply = complainReply;
    }

    public Integer getEventChannel() {
        return eventChannel;
    }

    public void setEventChannel(Integer eventChannel) {
        this.eventChannel = eventChannel;
    }

    public List<String> getReports() {
        return reports;
    }

    public void setReports(List<String> reports) {
        this.reports = reports;
    }
}
