package com.simon.dal.dao;

import java.util.List;

import com.simon.dal.model.Notice;
import com.simon.dal.vo.BaseClaims;
import com.simon.dal.vo.BaseQueryParam;
import org.apache.ibatis.annotations.Param;

public interface NoticeMapper {
    int deleteByPrimaryKey(String noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String noticeId);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);
    
    List<Notice> list(@Param("baseClaims") BaseQueryParam baseClaims, @Param("noticeType") Integer noticeType);

    List<Notice> touchList(BaseClaims baseClaims);
}