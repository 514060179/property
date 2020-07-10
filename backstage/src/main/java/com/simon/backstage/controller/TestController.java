package com.simon.backstage.controller;
import com.google.common.collect.Lists;
import java.util.Date;

import com.simon.backstage.dao.UserUnitMapper;
import com.simon.dal.dao.UserMapper;
import com.simon.dal.model.Community;
import java.math.BigDecimal;

import com.simon.backstage.dao.UnitMapper;
import com.simon.backstage.domain.model.Building;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.simon.backstage.domain.model.UserUnit;

import com.simon.backstage.domain.model.Unit;
import com.simon.dal.model.User;
import com.simon.dal.util.EncryUtil;
import com.simon.dal.util.UUIDUtil;
import org.apache.poi.ss.usermodel.Cell;
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


/**
 * @author fengtianying
 * @date 2019/5/20 14:40
 */
@RestController
@RequestMapping("excel")
public class TestController {

    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserUnitMapper userUnitMapper;
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
        Map<String,User> map = new HashMap<String,User>();
        for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
            XSSFRow xssfRow = xssfSheet.getRow(i);
            XSSFCell username = xssfRow.getCell(0);
            XSSFCell name = xssfRow.getCell(1);
            XSSFCell ename = xssfRow.getCell(2);
            XSSFCell sex = xssfRow.getCell(3);
            XSSFCell communityId = xssfRow.getCell(4);
            XSSFCell quhao = xssfRow.getCell(5);
            XSSFCell tel = xssfRow.getCell(6);
            if(tel != null){
                tel.setCellType(Cell.CELL_TYPE_STRING);
            }
            XSSFCell hunyinzhidu = xssfRow.getCell(7);
            XSSFCell peiou = xssfRow.getCell(8);
            XSSFCell zhidu = xssfRow.getCell(9);
            XSSFCell unitNo = xssfRow.getCell(10);

            UserUnit userUnit = new UserUnit();
            Map<String,UserUnit> mapUnit = new HashMap<String,UserUnit>();
            User user = new User();

            String marriageSystem = null;
            switch (hunyinzhidu.getStringCellValue()){
                case "S":marriageSystem = "S"+zhidu.getStringCellValue();break;
                default:marriageSystem=hunyinzhidu.getStringCellValue();
            }
            user.setUserId(UUIDUtil.uidString());
            user.setCommunityId(communityId.getStringCellValue());
            user.setName(name.getStringCellValue());
            user.setEnglishName(ename.getStringCellValue());
            user.setSex("男".equals(sex.getStringCellValue())?1:0);
            user.setCountryCode("86");
            user.setTel(tel==null?null:tel.getStringCellValue());
            user.setBirthday(new Date());
            user.setUsername(username.getStringCellValue());
            user.setPassword(EncryUtil.getMD5("123456"));
            user.setMarriageSystem(marriageSystem);
            user.setMateName(peiou==null?null:peiou.getStringCellValue());

            if (!map.containsKey(user.getName())){
                userMapper.insertSelective(user);
                userMapper.insertUserCommunity(user);
                map.put(user.getName(),user);
            }
            System.out.println(user);
            //获取单元id
            Unit unit = unitMapper.selectByUnitNo(unitNo.getStringCellValue(),communityId.getStringCellValue());
            if (unit!=null){
//                userUnit.setUserUnitId(UUIDUtil.uidString());
                userUnit.setUserId(map.get(user.getName()).getUserId());
                userUnit.setUnitId(unit.getUnitId());
                if (unit.getOwner()){
                    userUnit.setOwner(false);
                    userUnit.setConvincing(false);
                }else{
                    userUnit.setOwner(true);
                    userUnit.setConvincing(true);
                }
//                mapUnit.put(unitNo.getStringCellValue(),userUnit);
                System.out.println(userUnit);
                userUnitMapper.insertSelective(userUnit);
            }
        }

    }


}
