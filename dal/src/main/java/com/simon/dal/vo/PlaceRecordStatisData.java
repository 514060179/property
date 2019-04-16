package com.simon.dal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/4/16 13:47
 */
@ApiModel(value = "PlaceRecordStatisData", description = "场所预定统计返回model")
public class PlaceRecordStatisData {

    @ApiModelProperty(value = "场所id",example = "12312123456sd")
    private String placeId;
    @ApiModelProperty(value = "场所名字",example = "12312123456sd")
    private String placeName;
    @ApiModelProperty(value = "年月",example = "2019-03")
    private String yearMonth;
    @ApiModelProperty(value = "小时数",example = "20")
    private Integer hours;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
