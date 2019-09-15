package com.simon.backstage.dao;

import com.simon.backstage.domain.vo.ExcelUnit;
import com.simon.backstage.domain.vo.UnitQueryParam;
import com.simon.dal.model.User;
import com.simon.dal.vo.BaseQueryParam;
import org.apache.ibatis.annotations.Param;

import com.simon.backstage.domain.model.UserUnit;

import java.util.List;

public interface UserUnitMapper {
    int deleteByPrimaryKey(Long userUnitId);

    int insert(UserUnit record);

    int insertSelective(UserUnit record);

    int batchAddUser(List<UserUnit> userUnitList);

    UserUnit selectByPrimaryKey(Long userUnitId);

    UserUnit selectByUnitId(String unitId);

    int updateByPrimaryKeySelective(UserUnit record);

    int updateByPrimaryKey(UserUnit record);

	int deleteByUser(@Param("unitId") String unitId,@Param("userId") String userId);

	List<User> unitUserList(@Param("unitId") String unitId);

    List<ExcelUnit> getExcelUnits(BaseQueryParam baseQueryParam);

}