package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Visitor;

public interface VisitorMapper {
    int deleteByPrimaryKey(String visitorId);

    int insert(Visitor record);

    int insertSelective(Visitor record);

    Visitor selectByPrimaryKey(String visitorId);

    int updateByPrimaryKeySelective(Visitor record);

    int updateByPrimaryKey(Visitor record);
}