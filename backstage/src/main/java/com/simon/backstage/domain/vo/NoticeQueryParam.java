package com.simon.backstage.domain.vo;

import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author simon feng
 * @date 2019-08-29 15:19
 * @description 公告查询参数
 */
public class NoticeQueryParam extends BaseQueryParam {

    @ApiModelProperty(value="公告类型0通告1节日提醒2注意事项3政府文件4外判公司须知5工程6办理手续", example="0")
    private Integer noticeType;

    @ApiModelProperty(value = "发布时间（创建时间）的开始时间",example = "2019-08-20 10:00:00")
    private Date startTime;

    @ApiModelProperty(value = "发布时间（创建时间）的结束时间",example = "2019-08-22 15:00:00")
    private Date endTime;

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
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
