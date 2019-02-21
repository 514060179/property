package com.simon.backstage.domain.vo;

import java.math.BigDecimal;

/**
 * @author fengtianying
 * @date 2019/2/21 15:02
 */
public class ExcelCharge {

    private String recordDate;
    private BigDecimal amount;
    private String remark;

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
