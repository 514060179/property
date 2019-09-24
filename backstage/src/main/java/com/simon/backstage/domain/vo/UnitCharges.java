package com.simon.backstage.domain.vo;

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
public class UnitCharges {

    private List<String> xDateList;

    private List<String> xUbitList;

    private List<UnitChargeVo> chargeVoList;

    public List<String> getxDateList() {

        return getMonthStr();
    }

    public void setxDateList(List<String> xDateList) {
        this.xDateList = xDateList;
    }

    public List<String> getxUbitList() {
        return xUbitList;
    }

    public void setxUbitList(List<String> xUbitList) {
        this.xUbitList = xUbitList;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.setTime(new Date());
        try {
            start.setTime(sdf.parse("2016/01"));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("时间日期转换有误");
        }
        int result = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        int month = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }
}
