package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.UnitMapper;
import com.simon.backstage.dao.UserUnitMapper;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.model.UserUnit;
import com.simon.backstage.domain.vo.ExcelUnit;
import com.simon.backstage.domain.vo.UnitQueryParam;
import com.simon.backstage.service.UnitService;
import com.simon.dal.model.User;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;
import com.simon.dal.vo.BaseQueryParam;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private UserUnitMapper userUnitMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Unit add(Unit unit) {
        unit.setUnitId(UUIDUtil.uidString());
        unitMapper.insertSelective(unit);
        return unit;
    }

    @Override
    public Unit detail(String unitId) {
        return unitMapper.selectByPrimaryKey(unitId);
    }

    @Override
    public int upd(Unit unit) {
        return unitMapper.updateByPrimaryKeySelective(unit);
    }

    @Override
    public int updUserUnit(UserUnit userUnit) {
        return userUnitMapper.updateByPrimaryKeySelective(userUnit);
    }

    @Override
    public int del(String unitId) {
        return unitMapper.deleteByPrimaryKey(unitId);
    }

    @Override
    public PageInfo<Unit> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(unitMapper.selectByCondition(baseQueryParam));
    }
    

	@Override
	public UserUnit addUser(UserUnit userUnit) {
		int result = userUnitMapper.insertSelective(userUnit);
		if(result > 0){
			return userUnit;
		}
		return null;
	}

    @Override
    public int batchAddUser(List<UserUnit> userUnitList) {
        return userUnitMapper.batchAddUser(userUnitList);
    }

    @Override
    public UserUnit findUserUnitByUnitId(String unitId) {
        return userUnitMapper.selectByUnitId(unitId);
    }

    @Override
	public int delUser(String unitId, String userId) {
		// TODO Auto-generated method stub
		return userUnitMapper.deleteByUser(unitId, userId);
	}

    @Override
    public List<User> unitUserList(String unitId) {
        return userUnitMapper.unitUserList(unitId);
    }

    @Override
    public String importExcel(HttpServletRequest request,String communityId, String buildingId) {
        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
        StringBuffer responseMsg = new StringBuffer();
        files.forEach(multipartFile -> {
            int success = 0;
            int fail = 0;
            InputStream is =null;
            try {
                is = multipartFile.getInputStream();
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
                List<Unit> unitList = new ArrayList<>();
                StringBuffer failMsg = new StringBuffer("");
                for (int i = 1; i < xssfSheet.getLastRowNum(); i++) {
                    XSSFRow xssfRow = xssfSheet.getRow(i);
                    if(xssfRow==null || invalidData(xssfRow)){
                        continue;
                    }
                    XSSFCell yongtu = xssfRow.getCell(0);
                    XSSFCell bianhao = xssfRow.getCell(1);
                    XSSFCell mingcheng = xssfRow.getCell(2);
                    XSSFCell quanzhi = xssfRow.getCell(3);
                    XSSFCell weizhi = xssfRow.getCell(4);
                    XSSFCell status = xssfRow.getCell(5);
                    XSSFCell baifenbi = xssfRow.getCell(6);
                    XSSFCell yequan = xssfRow.getCell(7);
                    Unit unit = new Unit();
                    unit.setUnitId(UUIDUtil.uidString());
                    unit.setCommunityId(communityId);
                    unit.setBuildingId(buildingId);
                    unit.setUnitName(String.valueOf(mingcheng.getStringCellValue()));
                    unit.setUnitNo(String.valueOf(bianhao.getStringCellValue()));
                    unit.setUnitRelativeProportion(new BigDecimal(baifenbi.getNumericCellValue()).multiply(new BigDecimal(100)));
                    unit.setUnitPosition(String.valueOf(weizhi.getStringCellValue()));
                    int unitType = 1;//1商业2轻型汽车/电单车3住宅
                    switch (yongtu.getStringCellValue()){
                        case "商业":unitType=1;break;
                        case "轻型汽车/电单车":unitType=2;break;
                        case "住宅":unitType=3;break;
                    }
                    int statusInt = 0;//0空置1租赁2装修中3入
                    switch (status.getStringCellValue()){
                        case "空置":statusInt=0;break;
                        case "租赁":statusInt=1;break;
                        case "装修中":statusInt=2;break;
                        case "入住":statusInt=3;break;
                    }
                    unit.setUnitType(unitType);
                    unit.setUnitFullAddress(String.valueOf(quanzhi.getStringCellValue()));
                    unit.setUnitStatus(statusInt);
                    unit.setUnitTitle((int)yequan.getNumericCellValue());
                    unitList.add(unit);
                    try {
                        unitMapper.insertSelective(unit);
                        success++;
                    } catch (Exception e) {
                        fail++;
                        failMsg.append(i+1);
                        logger.error("导入excel失败 no=",i+1);
                        e.printStackTrace();
                    }
                }
                responseMsg.append("成功导入：").append(success).append("导入失败：").append(fail).append(failMsg);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("导入excel异常",e.getMessage());
            }finally {
                if(is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return responseMsg.toString();
    }

    @Override
    public List<ExcelUnit> getExcelUnits(UnitQueryParam unitQueryParam) {
        return userUnitMapper.getExcelUnits(unitQueryParam);
    }

    //判断是否为空行
    private boolean invalidData(XSSFRow row){
        for(int i = 0; i < row.getLastCellNum(); i++){
            XSSFCell cell = row.getCell(i);
            if(cell!=null && !StringUtils.isEmpty(cell.getStringCellValue())){
                return false;
            }
        }
        return true;
    }
}
