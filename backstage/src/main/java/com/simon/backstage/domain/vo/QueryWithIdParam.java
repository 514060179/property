package com.simon.backstage.domain.vo;

import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.ApiModel;

/**
 * @author fengtianying
 * @date 2019/1/27 16:06
 */
@ApiModel(value = "QueryWithIdParam", description = "查询条件id")
public class QueryWithIdParam extends BaseQueryParam{

    private String unitId;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
}
