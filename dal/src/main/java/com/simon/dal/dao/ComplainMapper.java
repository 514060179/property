package com.simon.dal.dao;

import java.util.List;

import com.simon.dal.model.Complain;
import com.simon.dal.vo.BaseClaims;

public interface ComplainMapper {
    int deleteByPrimaryKey(String complainId);

    int insert(Complain record);

    int insertSelective(Complain record);

    Complain selectByPrimaryKey(String complainId);

    int updateByPrimaryKeySelective(Complain record);

    int updateByPrimaryKeyWithBLOBs(Complain record);

    int updateByPrimaryKey(Complain record);
	
	List<Complain> list(BaseClaims baseClaims);
}