package com.simon.dal.dao;

import com.simon.dal.model.UserWithCommunity;
import com.simon.dal.vo.BaseClaims;

import com.simon.dal.model.User;
import com.simon.dal.vo.BaseQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int deleteUserCommunity(@Param("userId") String userId,@Param("communityId") String communityId);

    int insert(User record);

    int insertSelective(User record);

    int insertUserCommunity(User record);

    int delUserCommunity(String userId);

    User selectByPrimaryKey(String userId);

    List<UserWithCommunity> selectUserCommunitys(String userId);

    List<User> selectByCondition(BaseQueryParam baseQueryParam);

    List<Map<String,Object>> excrlUserList(BaseQueryParam baseQueryParam);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectUser(User record);

    List<User> selectBirthdayUser();
}