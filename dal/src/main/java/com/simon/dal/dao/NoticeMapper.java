package com.simon.dal.dao;

import java.util.List;

import com.simon.dal.model.Notice;
import com.simon.dal.vo.BaseClaims;

public interface NoticeMapper {
    int deleteByPrimaryKey(String noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String noticeId);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);
    
    List<Notice> list(BaseClaims baseClaims);
}