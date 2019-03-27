package com.simon.backstage.domain.vo;

import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/1/25 9:17
 */
@ApiModel(value = "EventQueryParam", description = "时间查询条件")
public class EventQueryParam extends BaseQueryParam {

    @ApiModelProperty(value = "社区id",example = "")
    private String communityId;

    @ApiModelProperty(value = "事件类型",example = "1采购2保养3其他")
    private Integer eventType;

    @ApiModelProperty(value = "提醒周期（单位：天）默认15",example = "15")
    private Integer eventRemindCycle;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Integer getEventRemindCycle() {
        return eventRemindCycle;
    }

    public void setEventRemindCycle(Integer eventRemindCycle) {
        this.eventRemindCycle = eventRemindCycle;
    }
}
