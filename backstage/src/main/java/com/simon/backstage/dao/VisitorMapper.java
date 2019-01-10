package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Visitor;
import com.simon.dal.vo.BaseClaims;

import java.util.List;

public interface VisitorMapper {
    int deleteByPrimaryKey(String visitorId);

    int insert(Visitor record);

    int insertSelective(Visitor record);

    Visitor selectByPrimaryKey(String visitorId);

    List<Visitor> selectByCondition(BaseClaims baseClaims);

    int updateByPrimaryKeySelective(Visitor record);

    int updateByPrimaryKey(Visitor record);
}