package com.simon.backstage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author simon feng
 * @date 2019-10-12 00:43
 * @description 物業收費修改vo(單元格)
 */
@ApiModel(value = "UnitChargesUpdVo", description = "物业收费表格修改model")
public class UnitChargesUpdVo {

    @ApiModelProperty("社區id")
    @NotNull
    private String communityId;

    @ApiModelProperty("记录类型0物业费1基金收费2订场收费3其他收费")
    @NotNull
    private Integer recordType;

    @NotNull
    private List<UnitChargeVo> unitChargeVos;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public List<UnitChargeVo> getUnitChargeVos() {
        return unitChargeVos;
    }

    public void setUnitChargeVos(List<UnitChargeVo> unitChargeVos) {
        this.unitChargeVos = unitChargeVos;
    }
}
