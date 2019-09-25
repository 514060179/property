package com.simon.backstage.service.impl;
import java.math.BigDecimal;
import com.simon.backstage.domain.model.ChargeItem;
import com.simon.dal.model.User;
import java.util.Date;
import com.simon.backstage.domain.vo.Community;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.AdvanceMoneyMapper;
import com.simon.backstage.dao.AdvanceRecordMapper;
import com.simon.backstage.dao.ChargeItemMapper;
import com.simon.backstage.dao.ChargeItemRecordMapper;
import com.simon.backstage.domain.model.AdvanceMoney;
import com.simon.backstage.domain.model.AdvanceRecord;
import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.vo.QueryWithIdParam;
import com.simon.backstage.domain.vo.UnitChargeVo;
import com.simon.backstage.domain.vo.UnitCharges;
import com.simon.backstage.service.ChargeItemRecordService;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fengtianying
 * @date 2019/1/27 14:43
 */
@Service
public class ChargeItemRecordServiceImpl implements ChargeItemRecordService {

    @Autowired
    private ChargeItemRecordMapper chargeItemRecordMapper;
    @Autowired
    private ChargeItemMapper chargeItemMapper;
    @Autowired
    private AdvanceMoneyMapper advanceMoneyMapper;
    @Autowired
    private AdvanceRecordMapper advanceRecordMapper;
    @Override
    public ChargeItemRecord add(ChargeItemRecord chargeItemRecord) {
        chargeItemRecord.setRecordId(UUIDUtil.uidString());
        if (chargeItemRecordMapper.insertSelective(chargeItemRecord)>0){
            return chargeItemRecord;
        }
        return null;
    }

    @Transactional
    @Override
    public int addBatch(List<ChargeItemRecord> chargeItemRecordList, List<AdvanceMoney> advanceMonies, List<AdvanceRecord> advanceRecords) {
        //1.更新账户
        if (!Objects.isNull(advanceMonies)&&advanceMonies.size()>0)
            advanceMonies.forEach(advanceMoney -> advanceMoneyMapper.updateByUserId(advanceMoney));
        //2.更新账户记录
        if (!Objects.isNull(advanceRecords)&&advanceRecords.size()>0)
            advanceRecordMapper.batchAdd(advanceRecords);
        //3.批量加入收费记录
        chargeItemRecordMapper.addBatch(chargeItemRecordList);
        //4.更新所有临时订单
        chargeItemMapper.updateAllTemporary();
        return 1;
    }

    @Override
    public ChargeItemRecord detail(String recordId) {
        return chargeItemRecordMapper.selectByPrimaryKey(recordId);
    }

    @Override
    public int upd(ChargeItemRecord chargeItemRecord) {
        return chargeItemRecordMapper.updateByPrimaryKeySelective(chargeItemRecord);
    }

    @Override
    public int del(String recordId) {
        return chargeItemRecordMapper.deleteByPrimaryKey(recordId);
    }

    @Override
    public int charge(List<String> recordIdList) {
        if (recordIdList == null || recordIdList.size() <= 0) {
            return 0;
        }
        return chargeItemRecordMapper.charge(recordIdList);
    }

    @Override
    public PageInfo<ChargeItemRecord> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(chargeItemRecordMapper.selectByCondition(baseQueryParam));
    }

    @Override
    public UnitCharges unitChargeList(String communityId) {
        BaseQueryParam baseQueryParam = new QueryWithIdParam();
        baseQueryParam.setCommunityId(communityId);
        List<ChargeItemRecord> list = chargeItemRecordMapper.selectByCondition(baseQueryParam);
        List<UnitChargeVo>  chargeVoList = new ArrayList<>();
        List<String>  unitList = new ArrayList<>();
        UnitCharges unitCharges = new UnitCharges();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM");

        list.forEach(chargeItemRecord -> {
            unitList.add(chargeItemRecord.getUnitNo());
            UnitChargeVo unitChargeVo = new UnitChargeVo();
            try {
                unitChargeVo.setxDate(sdf.format(sdf2.parse(chargeItemRecord.getRecordDate())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            unitChargeVo.setyUnit(chargeItemRecord.getUnitNo());
            unitChargeVo.setV1Date(sdf1.format(chargeItemRecord.getRecordTime()));
            unitChargeVo.setV2Money(chargeItemRecord.getRecordActualAmount().toString());
            chargeVoList.add(unitChargeVo);
        });
        unitCharges.setxUnitList(unitList);
        unitCharges.setChargeVoList(chargeVoList);
        //开始，结束时间 todo
//        unitCharges.setStartTime();
//        unitCharges.setEndTime();
        return unitCharges;
    }

    @Override
    public String importExcel(MultipartFile file, String communityId) {
        int success = 0;
        int fail = 0;
        InputStream is =null;
        try {
            is = file.getInputStream();
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            List<Unit> unitList = new ArrayList<>();
            StringBuffer failMsg = new StringBuffer("");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM");

            Map<String,String> map = new HashMap();
            for (int i = 0; i <= xssfSheet.getLastRowNum(); i++) {
                XSSFRow xssfRow = xssfSheet.getRow(i);
                int last = xssfRow.getLastCellNum();
                System.out.println("这是第"+i+"行。值分别：");
                String unitNo = null;
                for (int j = 0 ; j <= last ; j++){
                    //获取表头日期
                    if (i == 0 && j >= 4 && j % 2 == 0) {
                        XSSFCell xssfCell = xssfRow.getCell(j);
                        String headder = getCellValue(xssfCell) + "";
                        if ("".equals(headder)){
                            continue;
                        }
                        map.put(j+"", headder);
                    } else if (j >= 4 && j % 2 == 0) {
                        String date = map.get(j+"");//物业收费日期
                        XSSFCell d = xssfRow.getCell(j);//缴费日期
                        XSSFCell m = xssfRow.getCell(j+1);//缴费金额
                        if (d == null || m == null){
                            continue;
                        }
                        ChargeItemRecord chargeItemRecord = new ChargeItemRecord();
                        chargeItemRecord.setRecordId(UUIDUtil.uidString());
                        chargeItemRecord.setUserId("");
                        chargeItemRecord.setRecordItemName("導入數據收費");
                        chargeItemRecord.setRecordDate(date);
                        chargeItemRecord.setRecordStatus(1);
//                        chargeItemRecord.setUserId();
                        chargeItemRecord.setRecordTime(simpleDateFormat.parse(getCellValue(d)));
                        chargeItemRecord.setRecordActualAmount(new BigDecimal(getCellValue(m)));
                        chargeItemRecord.setRecordAmount(new BigDecimal(getCellValue(m)));
                        chargeItemRecord.setRecordRemark("導入數據收費");
                        chargeItemRecord.setUnitType(0);
                        chargeItemRecord.setUnitNo(unitNo);
                        chargeItemRecord.setCreateTime(new Date());
                    }else if(i > 0 && j == 0){//单位号
                        XSSFCell xssfCell = xssfRow.getCell(j);
                        //获取单位号
                        unitNo = getCellValue(xssfCell);
                    }else {
                        continue;

                    }
                }
                System.out.println();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String getCellValue(XSSFCell cell) {
        String strCell = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING://字符串类型
                    strCell = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC://数字类型
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                        SimpleDateFormat sdf = null;
// short dataFormat = cell.getCellStyle().getDataFormat();
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = cell.getDateCellValue();
                        strCell = sdf.format(date);
                    } else {
                        strCell = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN://boolean类型
                    strCell = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    strCell = "";
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    strCell = cell.getCTCell().getV();
                    break;
                default:
                    strCell = "";
                    break;
            }
        }
        if (strCell.equals("") || strCell == null) {
            strCell = "";
        }
        return strCell.trim();
    }


    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM");
        System.out.println(simpleDateFormat.parse("21-12"));
    }
}
