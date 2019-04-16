package com.simon.backstage.domain.vo;

import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author fengtianying
 * @date 2019/4/16 10:38
 */
@ApiModel(value = "PlaceRecordQueryParam", description = "订场记录查询条件")
public class PlaceRecordQueryParam extends BaseQueryParam {

    @ApiModelProperty(value="社区编号",example="213123sadaf")
    private String communityNo;

    @ApiModelProperty(value="预订日期",example="2018-08-08")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date orderDate;

    public String getCommunityNo() {
        return communityNo;
    }

    public void setCommunityNo(String communityNo) {
        this.communityNo = communityNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
