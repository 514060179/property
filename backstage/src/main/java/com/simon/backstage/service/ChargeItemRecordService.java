package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.AdvanceMoney;
import com.simon.backstage.domain.model.AdvanceRecord;
import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.backstage.domain.vo.UnitChargeVo;
import com.simon.backstage.domain.vo.UnitCharges;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author fengtianying
 * @date 2019/1/27 14:41
 */
public interface ChargeItemRecordService {

    /**
     * 新增
     * @param chargeItemRecord
     * @return
     */
    ChargeItemRecord add(ChargeItemRecord chargeItemRecord);
    /**
     * 批量新增
     * @param chargeItemRecordList
     * @return
     */
    int addBatch(List<ChargeItemRecord> chargeItemRecordList,List<AdvanceMoney> advanceMonies,List<AdvanceRecord> advanceRecords);

    /**
     * 详情
     * @param recordId
     * @return
     */
    ChargeItemRecord detail(String recordId);

    /**
     * 修改
     * @param chargeItemRecord
     * @return
     */
    int upd(ChargeItemRecord chargeItemRecord);

    /**
     * 删除
     * @param recordId
     * @return
     */
    int del(String recordId);

    /**
     * 设置成为已收费
     * @param recordIdList
     * @return
     */
    int charge(List<String> recordIdList);

    /**
     * 列表
     * @param baseQueryParam
     * @return
     */
    PageInfo<ChargeItemRecord> list(BaseQueryParam baseQueryParam);
    /**
     * 单元收费列表
     * @param communityId
     * @return
     */
    UnitCharges unitChargeList(String communityId);


    /**
     * 导入物业收费
     * @param file
     * @param communityId
     * @return
     */
    String importExcel(MultipartFile file, String communityId);
}
