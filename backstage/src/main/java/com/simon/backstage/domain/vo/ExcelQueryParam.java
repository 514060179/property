package com.simon.backstage.domain.vo;

import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/2/21 15:55
 */
public class ExcelQueryParam extends BaseQueryParam {

    @ApiModelProperty(value="收费状态0欠费1已付2预支付")
    private Integer recordStatus = 0;

    public Integer getRecordStatus() {
        return recordStatus==null?0:recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }
}
