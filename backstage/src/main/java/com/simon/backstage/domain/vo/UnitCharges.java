package com.simon.backstage.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author simon feng
 * @date 2019-09-24 20:16
 * @description 物业收费excel
 */
@ApiModel(value = "UnitCharges", description = "物业收费表头+表格")
public class UnitCharges {
    //x轴-日期列表
    @ApiModelProperty(value="x轴-日期列表")
    private List<String> xDateList;
    @ApiModelProperty(value="y轴-单元列表")
    private List<String> xUnitList;
    @ApiModelProperty(value="表格值")
    private List<UnitChargeVo> chargeVoList;
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Date endTime=new Date();
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Date startTime=new Date();

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public List<String> getxDateList() {

        return this.xDateList;
    }

    public void setxDateList(List<String> xDateList) {
        this.xDateList = xDateList;
    }

    public List<String> getxUnitList() {
        return xUnitList;
    }

    public void setxUnitList(List<String> xUnitList) {
        this.xUnitList = xUnitList;
    }

    public List<UnitChargeVo> getChargeVoList() {
        return chargeVoList;
    }

    public void setChargeVoList(List<UnitChargeVo> chargeVoList) {
        this.chargeVoList = chargeVoList;
    }

    //获取月份字符串集合
    private List<String> getMonthStr(){
        int month = getDifMonth();
        List<String> monthList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        for (int i = month ; i > 0 ; i--){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH,-i);
            String m = sdf.format(calendar.getTime());
            monthList.add(m);
        }
        monthList.add(sdf.format(new Date()));
        return monthList;
    }

    private int getDifMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        try {
            if (startTime==null){
                startTime = sdf.parse("2016年01月");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("时间日期转换有误");
        }
        start.setTime(startTime);
        int result = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        int month = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        try {
            Date d = sdf.parse("2019/07");
            System.out.println(sdf1.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
