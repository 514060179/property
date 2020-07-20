package com.simon.backstage.dao;

import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.dal.vo.BaseQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChargeItemRecordMapper {
    int deleteByPrimaryKey(String recordId);

    int insert(ChargeItemRecord record);

    int insertSelective(ChargeItemRecord record);

    int addBatch(List<ChargeItemRecord> list);

    ChargeItemRecord selectByPrimaryKey(String recordId);

    ChargeItemRecord selectByPlaceRecordId(String placeRecordId);

    List<ChargeItemRecord> selectByCondition(BaseQueryParam baseQueryParam);

    List<ChargeItemRecord> selectMaxRecord();

    List<ChargeItemRecord> selectExcelCondition(@Param("communityId") String communityId,@Param("recordType") int recordType,@Param("dateStart") String dateStart,@Param("dateEnd") String dateEnd);

    int updateByPrimaryKeySelective(ChargeItemRecord record);

    int updateByPrimaryKey(ChargeItemRecord record);

    int charge(List<String> list);
}