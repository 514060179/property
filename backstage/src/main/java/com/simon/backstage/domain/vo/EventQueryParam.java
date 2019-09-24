package com.simon.backstage.domain.vo;

import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/1/25 9:17
 */
@ApiModel(value = "EventQueryParam", description = "事件查询条件")
public class EventQueryParam extends BaseQueryParam {

    @ApiModelProperty(value = "社区id",example = "")
    private String communityId;

    @ApiModelProperty(value = "事件类型1采购2保养3其他",example = "1")
    private String eventType;

    @ApiModelProperty(value = "事件进度0跟进中1报价中2接获投诉3与管理机关讨论中4待定5工程进行中6待开大会表决7完成",example = "0")
    private String eventStatus;

    @ApiModelProperty(value = "物业资产编号",example = "ZC001")
    private String assetNo;

    @ApiModelProperty(value = "提醒周期（单位：天）默认15",example = "15")
    private Integer eventRemindCycle;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public Integer getEventRemindCycle() {
        return eventRemindCycle;
    }

    public void setEventRemindCycle(Integer eventRemindCycle) {
        this.eventRemindCycle = eventRemindCycle;
    }
}
