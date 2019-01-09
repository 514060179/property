package com.simon.dal.dao;

import com.simon.dal.model.Visitor;
import com.simon.dal.vo.BaseClaims;
import com.simon.dal.vo.VisitorWithToken;

import java.util.List;

public interface VisitorMapper {
    int deleteByPrimaryKey(String visitorId);

    int insert(Visitor record);

    int insertSelective(VisitorWithToken record);

    Visitor selectByPrimaryKey(String visitorId);

    List<Visitor> selectByCondition(BaseClaims baseClaims);

    int updateByPrimaryKeySelective(Visitor record);

    int updateByPrimaryKey(Visitor record);
}