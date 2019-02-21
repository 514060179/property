package com.simon.backstage.domain.vo;

import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/1/27 16:06
 */
@ApiModel(value = "QueryWithIdParam", description = "查询条件id")
public class QueryWithIdParam extends BaseQueryParam{

    @ApiModelProperty(value = "单元id",example = "2xzc3ty31u3xchiyt12sadkhg4sa0")
    private String unitId;

    @ApiModelProperty(value = "状态0欠费1已付2预支付",example = "0")
    private Integer recordStatus;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }
}
