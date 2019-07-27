package com.simon.app.dao;

import com.simon.app.model.vo.Building;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;


public interface BuildingMapper {



    List<Building> selectByCondition(BaseQueryParam baseQueryParam);


}