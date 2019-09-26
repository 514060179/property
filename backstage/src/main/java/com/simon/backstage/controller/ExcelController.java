package com.simon.backstage.controller;

import com.simon.backstage.domain.model.Asset;
import com.simon.backstage.domain.model.Event;
import com.simon.backstage.domain.vo.*;
import com.simon.backstage.service.*;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.ExcelUtils;
import com.simon.dal.model.PlaceRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.StringUtil;
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

    @Autowired
    private ExcelAssetService excelAssetService;

    @Autowired
    private ExcelEventService excelEventService;

    @Autowired
    private ExcelPlaceRecordService excelPlaceRecordService;

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
    public void assetExport(HttpServletResponse response, HttpServletRequest request, AssetQueryParam assetQueryParam){
         String communityId = ClaimsUtil.getCommunityId(request);
         if(!StringUtils.isEmpty(communityId)){
             assetQueryParam.setCommunityId(communityId);
         }
        List<Asset> assetsList = excelAssetService.execlAsset(assetQueryParam);
        ExcelData data = new ExcelData();
        data.setName("资产列表");
        List<String> titles = new ArrayList();
        titles.add("资产编号");
        titles.add("描述");
        titles.add("描述（英文）");
        titles.add("资产名称");
        titles.add("资产名称（英文）");
        titles.add("状态");
        titles.add("保养周期");
        titles.add("保固日期");
        titles.add("位置信息");
        titles.add("位置信息（英文）");
        titles.add("保养");
        titles.add("购买日期");
        titles.add("社区");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        assetsList.forEach(asset -> {
            List<Object> row = new ArrayList();
            row.add(asset.getAssetNo());
            row.add(asset.getAssetDescribe());
            row.add(asset.getAssetEnglishDescribe());
            row.add(asset.getAssetName());
            row.add(asset.getAssetEnglishName());
            row.add(asset.getAssetStatus());
            row.add(asset.getAssetMaintainRemindCycle());
            row.add(asset.getAssetOverdueDate());
            row.add(asset.getAssetPosition());
            row.add(asset.getAssetEnglishPosition());
            row.add(asset.getAssetMaintain());
            row.add(asset.getAssetBuyDate());
            if(!StringUtils.isEmpty(asset.getCommunity())){
                row.add(asset.getCommunity().getCommunityName());
            }else{
                row.add("-");
            }
            rows.add(row);
        });
        data.setRows(rows);
        try{
            ExcelUtils.exportExcel(response,"bms澳門物業资产",data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/eventExport")
    @ApiOperation("事件导出")
    public void eventExport(HttpServletResponse response, HttpServletRequest request, EventQueryParam eventQueryParam){
        String communityId = ClaimsUtil.getCommunityId(request);
        if(!StringUtils.isEmpty(communityId)){
            eventQueryParam.setCommunityId(communityId);
        }
        List<Event> eventList = excelEventService.excelEvent(eventQueryParam);
        ExcelData data = new ExcelData();
        data.setName("事件列表");
        List<String> titles = new ArrayList();
        titles.add("社区");
        titles.add("事件类型");
        titles.add("事件进度");
        titles.add("接收渠道");
        titles.add("事件起因");
        titles.add("事件内容");
        titles.add("事件日期");
        titles.add("完成时间");
        titles.add("备注");
        titles.add("解决方案");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        eventList.forEach(event -> {
            List<Object> row = new ArrayList();
            if(!StringUtils.isEmpty(event.getCommunity())){
                row.add(event.getCommunity().getCommunityName());
            }else{
                row.add("-");
            }
            row.add(event.getEventType());
            row.add(event.getEventDate());
            row.add(event.getEventStatus());
            row.add(event.getEventChannel());
            row.add(event.getEventCause());
            row.add(event.getEventContent());
            row.add(event.getEventDate());
            row.add(event.getEventFinishDate());
            row.add(event.getEventRemark());
            row.add(event.getEventSolve());
            rows.add(row);
        });
        data.setRows(rows);
        try{
            ExcelUtils.exportExcel(response,"bms澳門物業事件",data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/placeRecordExport")
    @ApiOperation("订场记录导出")
    public void placeRecordExport(HttpServletResponse response, HttpServletRequest request,
                                  PlaceRecordQueryParam placeRecordQueryParam){
        String communityId = ClaimsUtil.getCommunityId(request);
        if(!StringUtils.isEmpty(communityId)){
            placeRecordQueryParam.setCommunityId(communityId);
        }
        List<PlaceRecord> placeRecordList = excelPlaceRecordService.excelPlaceRecord(placeRecordQueryParam);
        ExcelData data = new ExcelData();
        data.setName("订场记录列表");
        List<String> titles = new ArrayList();
        titles.add("场所名称");
        titles.add("场所名称（英文）");
        titles.add("附加费用");
        titles.add("每小时费用");
        titles.add("创建事件");
        titles.add("预定时间");
        titles.add("结束时间");
        titles.add("开始时间");
        titles.add("预定状态");
        titles.add("总费用");
        titles.add("总时间");
        titles.add("更新时间");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        placeRecordList.forEach(placeRecord -> {
            List<Object> row = new ArrayList();
            row.add(placeRecord.getPlace().getPlaceName());
            row.add(placeRecord.getPlace().getPlaceEnglishName());
            row.add(placeRecord.getAttachCharge());
            row.add(placeRecord.getAverageCharge());
            row.add(placeRecord.getCreateTime());
            row.add(placeRecord.getOrderDate());
            row.add(placeRecord.getOrderEndTime());
            row.add(placeRecord.getOrderStartTime());
            row.add(placeRecord.getRecordStatus());
            row.add(placeRecord.getTotalCharge());
            row.add(placeRecord.getTotalHour());
            row.add(placeRecord.getUpdateTime());
            rows.add(row);
        });
        data.setRows(rows);
        try{
            ExcelUtils.exportExcel(response,"bms澳門物業订场记录",data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
