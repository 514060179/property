package com.simon.app.dao;

import com.simon.app.model.vo.Building;

import java.util.List;


public interface BuildingMapper {



    List<Building> selectByCondition(String communityId);


}