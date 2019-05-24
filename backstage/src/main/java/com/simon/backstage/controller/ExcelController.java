package com.simon.backstage.controller;

import com.simon.backstage.domain.vo.ExcelCharge;
import com.simon.backstage.domain.vo.ExcelData;
import com.simon.backstage.domain.vo.ExcelQueryParam;
import com.simon.backstage.domain.vo.ExcelUser;
import com.simon.backstage.service.ExcelUserService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author fengtianying
 * @date 2019/2/21 9:06
 */

@RestController
@RequestMapping("/back/excel")
@Api(value = "ExcelController", description = "excel导出")
public class ExcelController {


    @Autowired
    private ExcelUserService excelUserService;

    @GetMapping("/chargeExport")
    @ApiOperation("费用导出（未交/已收/预收）")
    public void chargeExport(HttpServletResponse response, HttpServletRequest request, ExcelQueryParam excelQueryParam){

        String communityId = ClaimsUtil.getCommunityId(request);
        if(!StringUtils.isEmpty(communityId)){
            excelQueryParam.setCommunityId(communityId);
        }
        List<ExcelUser> excelUserList = excelUserService.execlUser(excelQueryParam);
        String name = "";
        switch (excelQueryParam.getRecordStatus()) {
            case 0:
                name = "(未交)";
                break;
            case 1:
                name = "(已交)";
                break;
            case 2:
                name = "（預交）";
                break;
        }
        ExcelData data = new ExcelData();
        data.setName(name);
        List<String> titles = new ArrayList();
        titles.add("名字");
        List monthList = getMonthStr();
        titles.addAll(monthList);
        List<List<Object>> rows = new ArrayList();
        excelUserList.forEach(excelUser -> {
            List<Object> row = new ArrayList();
            row.add(excelUser.getName());
            monthList.forEach(month -> {
                BigDecimal amount = new BigDecimal(0);
                List<ExcelCharge> list = excelUser.getExcelChargeList();
                for (int i = 0 ; i < list.size() ; i++){
                    if (list.get(i).getRecordDate().equals(month)) {
                        amount = list.get(i).getAmount();
                        break;
                    }
                }
                row.add(amount);
            });
            row.add(excelUser.getAdvanceAmount()==null?0:excelUser.getAdvanceAmount());
            rows.add(row);
        });
        titles.add("預收剩餘費用");
        data.setTitles(titles);
        data.setRows(rows);
        try{
            ExcelUtils.exportExcel(response,"bms澳門物業費用收取",data);
        }catch (Exception e){
            e.printStackTrace();
        }
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
            start.setTime(sdf.parse("2019/01"));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("时间日期转换有误");
        }
        int result = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        int month = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }


    @GetMapping("/assetExport")
    @ApiOperation("资产导出")
    public void assetExport(HttpServletResponse response, HttpServletRequest request, ExcelQueryParam excelQueryParam){

    }

    @GetMapping("/eventExport")
    @ApiOperation("事件导出")
    public void eventExport(HttpServletResponse response, HttpServletRequest request, ExcelQueryParam excelQueryParam){

    }

    @GetMapping("/placeRecordExport")
    @ApiOperation("订场记录导出")
    public void placeRecordExport(HttpServletResponse response, HttpServletRequest request, ExcelQueryParam excelQueryParam){

    }
}
