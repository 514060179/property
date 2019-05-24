package com.simon.backstage.controller;
import java.math.BigDecimal;

import com.simon.backstage.dao.UnitMapper;
import com.simon.backstage.domain.model.Building;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.simon.backstage.domain.vo.Community;

import com.simon.backstage.domain.model.Unit;
import com.simon.dal.util.UUIDUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;


/**
 * @author fengtianying
 * @date 2019/5/20 14:40
 */
@RestController
@RequestMapping("excel")
public class TestController {

    @Autowired
    private UnitMapper unitMapper;
    @GetMapping(value = "/unit")
    public void unit() throws Exception {
        InputStream is = new FileInputStream("D:/unit.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        List<Unit> unitList = new ArrayList<>();
        for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
            XSSFRow xssfRow = xssfSheet.getRow(i);
            XSSFCell yongtu = xssfRow.getCell(0);
            XSSFCell bianhao = xssfRow.getCell(1);
            XSSFCell mingcheng = xssfRow.getCell(2);
            XSSFCell quanzhi = xssfRow.getCell(3);
            XSSFCell jianzhuid = xssfRow.getCell(4);
            XSSFCell weizhi = xssfRow.getCell(5);
            XSSFCell status = xssfRow.getCell(6);
            XSSFCell baifenbi = xssfRow.getCell(7);
            XSSFCell yequan = xssfRow.getCell(8);

            Unit unit = new Unit();
            unit.setUnitId(UUIDUtil.uidString());
            unit.setCommunityId("be478482b8a34dd8b39097cfc7701264");
            unit.setBuildingId(String.valueOf(jianzhuid.getStringCellValue()));
            unit.setUnitName(String.valueOf(mingcheng.getStringCellValue()));
            unit.setUnitNo(String.valueOf(bianhao.getStringCellValue()));

//            unit.setUnitCoveredArea(new BigDecimal("0"));
            unit.setUnitRelativeProportion(new BigDecimal(baifenbi.getNumericCellValue()).multiply(new BigDecimal(100)));
//            unit.setUnitChildRelativeProportion(new BigDecimal("0"));
//            unit.setUnitPurpose("");
            unit.setUnitPosition(String.valueOf(weizhi.getStringCellValue()));
            unit.setUnitType((int)yongtu.getNumericCellValue());
            unit.setUnitFullAddress(String.valueOf(quanzhi.getStringCellValue()));
            unit.setUnitStatus((int)status.getNumericCellValue());
            unit.setUnitTitle((int)yequan.getNumericCellValue());
            unitList.add(unit);
            unitMapper.insertSelective(unit);
        }

    }
    @GetMapping(value = "/user")
    public void user() throws Exception {
        InputStream is = new FileInputStream("D:/user.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        List<Unit> unitList = new ArrayList<>();
        for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
            XSSFRow xssfRow = xssfSheet.getRow(i);
            XSSFCell yongtu = xssfRow.getCell(0);
            XSSFCell bianhao = xssfRow.getCell(1);
            XSSFCell mingcheng = xssfRow.getCell(2);
            XSSFCell quanzhi = xssfRow.getCell(3);
            XSSFCell jianzhuid = xssfRow.getCell(4);
            XSSFCell weizhi = xssfRow.getCell(5);
            XSSFCell status = xssfRow.getCell(6);
            XSSFCell baifenbi = xssfRow.getCell(7);
            XSSFCell yequan = xssfRow.getCell(8);

            Unit unit = new Unit();
            unit.setUnitId(UUIDUtil.uidString());
            unit.setCommunityId("be478482b8a34dd8b39097cfc7701264");
            unit.setBuildingId(String.valueOf(jianzhuid.getStringCellValue()));
            unit.setUnitName(String.valueOf(mingcheng.getStringCellValue()));
            unit.setUnitNo(String.valueOf(bianhao.getStringCellValue()));

//            unit.setUnitCoveredArea(new BigDecimal("0"));
            unit.setUnitRelativeProportion(new BigDecimal(baifenbi.getNumericCellValue()).multiply(new BigDecimal(100)));
//            unit.setUnitChildRelativeProportion(new BigDecimal("0"));
//            unit.setUnitPurpose("");
            unit.setUnitPosition(String.valueOf(weizhi.getStringCellValue()));
            unit.setUnitType((int)yongtu.getNumericCellValue());
            unit.setUnitFullAddress(String.valueOf(quanzhi.getStringCellValue()));
            unit.setUnitStatus((int)status.getNumericCellValue());
            unit.setUnitTitle((int)yequan.getNumericCellValue());
            unitList.add(unit);
            unitMapper.insertSelective(unit);
        }

    }


}
