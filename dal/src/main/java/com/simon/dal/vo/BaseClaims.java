package com.simon.dal.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

public class BaseClaims extends BaseQueryParam {

	@ApiModelProperty(value="公告类型0通告1节日提醒2注意事项3政府文件4外判公司须知5工程6办理手续", example="0")
	private Integer noticeType;

	@ApiModelProperty(hidden=true)
	private String userId;


	@ApiModelProperty(value = "发布时间（创建时间）的开始时间",example = "2019-08-20 10:00:00")
	private String startTime;

	@ApiModelProperty(value = "发布时间（创建时间）的结束时间",example = "2019-08-22 15:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private String endTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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
}
