package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Advertisement;
import com.simon.dal.vo.BaseQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvertisementMapper {
    int deleteByPrimaryKey(String advId);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    int insertBatch(List<Advertisement> list);

    Advertisement selectByPrimaryKey(String advId);

    List<Advertisement> selectByConditon(BaseQueryParam baseQueryParam);

    List<Advertisement> selectUsable(@Param("communityId") String communityId, @Param("buildingId")String buildingId);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
}