package com.simon.backstage.domain.vo;

import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author fengtianying
 * @date 2019/4/11 16:49
 */
@ApiModel(value = "EventQueryParam", description = "资产查询条件")
public class AssetQueryParam extends BaseQueryParam {

    @ApiModelProperty(value = "保养周期（天数）",example = "15")
    private Integer assetMaintainRemindCycle;

    @ApiModelProperty(value = "保固日期",example = "2020-11-11",dataType = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date assetOverdueDate;

    @ApiModelProperty(value = "状态0使用中1货存2损坏3弃置",example = "0")
    private Integer assetStatus;

    public Integer getAssetMaintainRemindCycle() {
        return assetMaintainRemindCycle;
    }

    public void setAssetMaintainRemindCycle(Integer assetMaintainRemindCycle) {
        this.assetMaintainRemindCycle = assetMaintainRemindCycle;
    }

    public Date getAssetOverdueDate() {
        return assetOverdueDate;
    }

    public void setAssetOverdueDate(Date assetOverdueDate) {
        this.assetOverdueDate = assetOverdueDate;
    }

    public Integer getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(Integer assetStatus) {
        this.assetStatus = assetStatus;
    }
}
