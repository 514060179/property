package com.simon.backstage.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author simon feng
 * @date 2019-08-29 15:19
 * @description 公告查询参数
 */
public class NoticeQueryParam extends BaseQueryParam {

    @ApiModelProperty(value="公告类型0通告1节日提醒2注意事项3政府文件4外判公司须知5工程6办理手续", example="0")
    private Integer noticeType;

    @ApiModelProperty(value = "发布时间（创建时间）的开始时间",example = "2019-08-20 10:00:00")
    private String startTime;

    @ApiModelProperty(value = "用户id由前端app使用")
    private String userId;

    @ApiModelProperty(value = "发布时间（创建时间）的结束时间",example = "2019-08-22 15:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String endTime;

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
