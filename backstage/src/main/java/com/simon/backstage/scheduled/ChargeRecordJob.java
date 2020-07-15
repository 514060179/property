package com.simon.backstage.scheduled;

import com.simon.backstage.constant.Status;
import com.simon.backstage.domain.model.AdvanceMoney;
import com.simon.backstage.domain.model.AdvanceRecord;
import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.backstage.domain.vo.UnitItemWithUser;
import com.simon.backstage.service.AdvanceService;
import com.simon.backstage.service.ChargeItemRecordService;
import com.simon.backstage.service.ChargeItemService;
import com.simon.dal.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  添加收费记录
 * @author fengtianying
 * @date 2019/2/19 9:04
 */
@Component
@EnableScheduling
public class ChargeRecordJob {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ChargeItemRecordService chargeItemRecordService;
    @Autowired
    private ChargeItemService chargeItemService;
    @Autowired
    private AdvanceService advanceService;

    /**
     * 周期性记录插入
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void job1(){
        logger.info("定时任务周期性记录插入");
        //周期性收费项目
        List<UnitItemWithUser> unitItemWithUsers = chargeItemService.cycleUnitItem();
        //预支付账户
        List<AdvanceMoney> list = advanceService.allAdvanceMoney();
        Map<String,AdvanceMoney> maps = list.stream().collect(Collectors.toMap(AdvanceMoney::getUserId,advanceMoney -> advanceMoney));
        List<ChargeItemRecord> chargeItemRecords = new ArrayList<>();//收费记录
        List<AdvanceMoney> advanceMonies = new ArrayList<>();//账户
        List<AdvanceRecord> advanceRecords = new ArrayList<>();//账户记录
        unitItemWithUsers.forEach((unitItemWithUser)->{
            ChargeItemRecord chargeItemRecord = new ChargeItemRecord();
            chargeItemRecord.setRecordItemName(unitItemWithUser.getChargeItem().getItemName());
            AdvanceMoney advanceMoney = new AdvanceMoney();
            advanceMoney.setUserId(unitItemWithUser.getUserId());
            AdvanceRecord advanceRecord = new AdvanceRecord();
            chargeItemRecord.setRecordId(UUIDUtil.uidString());
            StringBuffer remarkSb = new StringBuffer();
            BigDecimal recordAmount = new BigDecimal(0);
            //收费方式
            if (Status.alculationMethodFixed==unitItemWithUser.getChargeItem().getAlculationMethod()){//固定收费
                recordAmount = unitItemWithUser.getChargeItem().getUnitPrice();
            }else if(Status.alculationMethodFormula==unitItemWithUser.getChargeItem().getAlculationMethod()){//公式收费
                //单位面积收费
                recordAmount = unitItemWithUser.getUnit().getUnitCoveredArea().multiply(unitItemWithUser.getChargeItem().getUnitPrice()).setScale(1,BigDecimal.ROUND_HALF_UP);
            }
            chargeItemRecord.setUnitId(unitItemWithUser.getUnit().getUnitId());
            chargeItemRecord.setUnitItemId(unitItemWithUser.getUnitItemId());
            chargeItemRecord.setUserId(unitItemWithUser.getUserId());
            BigDecimal additionalCost = unitItemWithUser.getChargeItem().getAdditionalCost();//额外收费
            chargeItemRecord.setRecordDate(recordDate());//收费日期
            //支付状态0欠费1已付2预支付
            if (maps.get(unitItemWithUser.getUserId())!=null){
                AdvanceMoney am = maps.get(unitItemWithUser.getUserId());
                if (maps.get(unitItemWithUser.getUserId()).getAdvanceAmount().compareTo(recordAmount.add(additionalCost == null ? new BigDecimal(0) : additionalCost)) >= 0) {//账户余额足够支付
                    chargeItemRecord.setRecordStatus(Status.recordStatusPaid);//已支付
                    advanceMoney.setAdvanceAmount(recordAmount.add(additionalCost == null ? new BigDecimal(0) : additionalCost));
                    advanceRecord.setAdvanceAmount(recordAmount.add(additionalCost == null ? new BigDecimal(0) : additionalCost));
                    chargeItemRecord.setRecordActualAmount(recordAmount.add(additionalCost));//实际收取金额
                    am.setAdvanceAmount(am.getAdvanceAmount().subtract(recordAmount.add(additionalCost == null ? new BigDecimal(0) : additionalCost)));
                    remarkSb.append("【"+unitItemWithUser.getChargeItem().getItemName()+"】:"+""+recordAmount+"-【預收金額】:"+recordAmount.add(additionalCost == null ? new BigDecimal(0) : additionalCost));
                }else{//不足
                    chargeItemRecord.setRecordStatus(Status.recordStatusOwe);//欠费
                    //费用减免
                    remarkSb.append("【"+unitItemWithUser.getChargeItem().getItemName()+"】:"+recordAmount+(maps.get(unitItemWithUser.getUserId()).getAdvanceAmount().compareTo(new BigDecimal(0))==0?"":"-"+"【預收金額】:"+maps.get(unitItemWithUser.getUserId()).getAdvanceAmount()));
                    recordAmount=recordAmount.subtract(maps.get(unitItemWithUser.getUserId()).getAdvanceAmount());
                    advanceMoney.setAdvanceAmount(maps.get(unitItemWithUser.getUserId()).getAdvanceAmount());
                    advanceRecord.setAdvanceAmount(maps.get(unitItemWithUser.getUserId()).getAdvanceAmount());
                    am.setAdvanceAmount(new BigDecimal(0));
                }
                maps.put(unitItemWithUser.getUserId(),am);
                remarkSb.append(additionalCost==null?"":"+【額外費用】:"+unitItemWithUser.getChargeItem().getAdditionalCost());
                advanceRecord.setAdvanceRecordId(UUIDUtil.uidString());
                advanceRecord.setAdvanceId(maps.get(unitItemWithUser.getUserId()).getAdvanceId());
                advanceRecord.setAdvanceType(Status.advanceTypeOut);
                advanceRecord.setAdvanceDescribe(remarkSb.toString());
                advanceMonies.add(advanceMoney);
                if(advanceRecord.getAdvanceAmount().compareTo(new BigDecimal(0))!=0){
                    advanceRecords.add(advanceRecord);
                }
            }else{
                chargeItemRecord.setRecordStatus(Status.recordStatusOwe);//欠费
                remarkSb.append("【"+unitItemWithUser.getChargeItem().getItemName()+"】:"+recordAmount).append(additionalCost == null ? "" : "+【額外費用】:"+additionalCost);
            }
            chargeItemRecord.setRecordRemark(remarkSb.toString());
            chargeItemRecord.setRecordAmount(additionalCost==null?recordAmount:recordAmount.add(additionalCost));
            chargeItemRecord.setCommunityId(unitItemWithUser.getChargeItem().getCommunityId());
            chargeItemRecord.setUnitNo(unitItemWithUser.getUnit().getUnitNo());
            chargeItemRecords.add(chargeItemRecord);
        });
        try {
            chargeItemRecordService.addBatch(chargeItemRecords,advanceMonies,advanceRecords);
        } catch (Exception e) {
            logger.error("周期性任务执行失败",e);
            e.printStackTrace();
        }
    }
    private String recordDate(){
        return new SimpleDateFormat("yyyy年MM月").format(new Date());
    }


}
