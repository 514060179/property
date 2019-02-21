package com.simon.backstage.controller;

import com.simon.backstage.domain.vo.ExcelData;
import com.simon.backstage.domain.vo.ExcelQueryParam;
import com.simon.backstage.domain.vo.ExcelUser;
import com.simon.backstage.service.ExcelUserService;
import com.simon.backstage.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengtianying
 * @date 2019/2/21 9:06
 */

@RestController
@RequestMapping("excel")
public class ExcelController {


    @Autowired
    private ExcelUserService excelUserService;

    @RequestMapping("/test1")
    public void test1(HttpServletResponse response, ExcelQueryParam excelQueryParam){
        List<ExcelUser> excelUserList = excelUserService.execlUser(excelQueryParam);
        ExcelData data = new ExcelData();
        data.setName("hello");
        List<String> titles = new ArrayList();
        titles.add("名字");
        List<List<Object>> rows = new ArrayList();
        excelUserList.forEach(excelUser ->{
            List<Object> row = new ArrayList();
            row.add(excelUser.getName());
            excelUser.getExcelChargeList().forEach(excelCharge -> {
                titles.add(excelCharge.getRecordDate());//日期
                row.add(excelCharge.getAmount());//总费用
            });
            rows.add(row);
        } );
        titles.add("备注");
        titles.add("预收费用");
        data.setTitles(titles);
        data.setRows(rows);
        try{
            ExcelUtils.exportExcel(response,"test2",data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    private List<String> list(){
//
//    }
    @RequestMapping("/test2")
    public void test2(HttpServletResponse response){
        ExcelData data = new ExcelData();
        data.setName("hello");
        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("userName");
        titles.add("password");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for(int i = 0;i<4;i++){
            List<Object> row = new ArrayList();
            row.add(i+"1");
            row.add(i+"2");
            row.add(i+"3");
            rows.add(row);
        }
        data.setRows(rows);
        try{
            ExcelUtils.exportExcel(response,"test2",data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
