package com.simon.backstage.service.impl;
import com.simon.backstage.dao.ChargeItemMapper;
import com.simon.backstage.dao.ChargeItemRecordMapper;
import com.simon.backstage.dao.UnitChargeItemMapper;
import com.simon.dal.model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.simon.backstage.domain.vo.Community;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.UnitMapper;
import com.simon.backstage.domain.model.ChargeItem;
import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.model.UnitChargeItem;
import com.simon.backstage.service.PlaceRecordService;
import com.simon.dal.dao.PlaceRecordMapper;
import com.simon.dal.model.PlaceRecord;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import com.simon.dal.vo.PlaceRecordStatisData;
import com.simon.dal.vo.PlaceRecordStatisQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author fengtianying
 * @date 2019/4/16 10:21
 */
@Service
public class PlaceRecordServiceImpl implements PlaceRecordService {

    @Autowired
    private PlaceRecordMapper placeRecordMapper;

    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private ChargeItemMapper chargeItemMapper;
    @Autowired
    private UnitChargeItemMapper unitChargeItemMapper;
    @Autowired
    private ChargeItemRecordMapper chargeItemRecordMapper;
    @Override
    public PlaceRecord add(PlaceRecord placeRecord) {
        return null;
    }

    @Override
    public PlaceRecord detail(String placeRecordId) {
        return placeRecordMapper.selectByPrimaryKey(placeRecordId);
    }

    @Override
    public int upd(PlaceRecord placeRecord) {
        return placeRecordMapper.updateByPrimaryKeySelective(placeRecord);
    }

    @Override
    public int del(String placeId) {
        return placeRecordMapper.deleteByPrimaryKey(placeId);
    }

    @Override
    public PageInfo<PlaceRecord> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(placeRecordMapper.selectByCondition(baseQueryParam));
    }

    @Override
    public List<PlaceRecordStatisData> statis(PlaceRecordStatisQuery placeRecordStatisQuery) {
        return placeRecordMapper.statis(placeRecordStatisQuery);
    }

    @Override
    public boolean creChargeItemRecord(String communityId,String userId, String placeRecordId, String unitId, BigDecimal price) {
        //1.生成收费项目
        //2.生成收费项目绑定关系
        //3.生成收费记录
        ChargeItem chargeItem = new ChargeItem();
        chargeItem.setCommunityId(communityId);
        String itemId = UUIDUtil.uidString();
        chargeItem.setItemId(itemId);
        chargeItem.setItemNo(System.currentTimeMillis()+"");
        chargeItem.setItemName("盯場收費");
        chargeItem.setBillingMode(1);
        chargeItem.setAlculationMethod(0);
        chargeItem.setUnitPrice(price);
        chargeItem.setAdditionalCost(new BigDecimal("0"));
        chargeItem.setLateFee(0);
        chargeItem.setLateDate(0);
        chargeItem.setFormula("");
        chargeItem.setDescribe("盯場收費列入物業收費");
        chargeItem.setRepeat(false);
        chargeItemMapper.insertSelective(chargeItem);
        UnitChargeItem unitChargeItem = new UnitChargeItem();
        String unitItemId = UUIDUtil.uidString();
        unitChargeItem.setUnitItemId(unitItemId);
        unitChargeItem.setItemId(itemId);
        unitChargeItem.setUnitId(unitId);
        unitChargeItem.setCreateTime(new Date());
        unitChargeItemMapper.insertSelective(unitChargeItem);
        ChargeItemRecord chargeItemRecord = new ChargeItemRecord();
        chargeItemRecord.setRecordId(UUIDUtil.uidString());
        chargeItemRecord.setUserId(userId);
        chargeItemRecord.setUnitItemId(unitItemId);
        chargeItemRecord.setRecordItemName("盯場收費");
        chargeItemRecord.setRecordDate(nextMonthDate(new Date()));
        chargeItemRecord.setRecordStatus(0);
        chargeItemRecord.setRecordTime(new Date());
        chargeItemRecord.setRecordActualAmount(new BigDecimal("0"));
        chargeItemRecord.setRecordAmount(price);
        chargeItemRecord.setRecordRemark("盯場收費");
        chargeItemRecordMapper.insertSelective(chargeItemRecord);
        return true;
    }
    public String nextMonthDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(calendar.MONTH, 1);
        return simpleDateFormat.format(calendar.getTime());
    }

    @Override
    public ChargeItemRecord getChargeItemRecord(String placeRecordId) {
        return chargeItemRecordMapper.selectByPrimaryKey(placeRecordId);
    }

    @Override
    public Unit getUnitByUserId(String userId) {
        return unitMapper.getUnitByUserId(userId) != null ? unitMapper.getUnitByUserId(userId).get(0) : null;
    }
}
