package com.simon.dal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/4/16 13:40
 */
@ApiModel(value = "PlaceRecordStatisQuery", description = "场所预定统计查询条件")
public class PlaceRecordStatisQuery {

    @ApiModelProperty(value = "社区id(超级管理员会用到)",example = "12312123456sd")
    private String communityId;

    @ApiModelProperty(value = "年月（不填则为所有）",example = "2019-03")
    private String yearMonth;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
}
