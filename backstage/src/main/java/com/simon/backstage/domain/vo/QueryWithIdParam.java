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

    @ApiModelProperty(value = "单元编号",example = "UNIT001")
    private String unitNo;

    @ApiModelProperty(value = "住户名字",example = "陈先生")
    private String userName;

    @ApiModelProperty(value = "状态0欠费1已付2预支付",example = "0")
    private Integer recordStatus;

    @ApiModelProperty(value = "记录类型0物业费1基金收费2订场收费3其他收费",example = "0")
    private Integer recordType;

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }
}
