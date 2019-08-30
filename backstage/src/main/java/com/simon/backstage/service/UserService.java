package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.dal.model.User;
import com.simon.dal.model.UserWithCommunity;
import com.simon.dal.vo.BaseClaims;
import com.simon.dal.vo.BaseQueryParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * @author fengtianying
 * @date 2018/11/7 13:32
 */
public interface UserService {

    /**
     * 新增
     * @param user
     * @return
     */
    User add(User user);

    /**
     * 新增
     * @param userId
     * @return
     */
    User detail(String userId);

    /**
     * 删除绑定的社区
     * @param userId
     * @param communityId
     * @return
     */
    int delUserCommunity(String userId,String communityId);

    /**
     * 添加绑定社区
     * @param userWithCommunityList
     * @return
     */
    int addUserCommunity(List<UserWithCommunity> userWithCommunityList);

    /**
     * 修改
     * @param user
     * @return
     */
    int upd(User user);

    /**
     * 删除
     * @param userId
     * @return
     */
    int del(String userId);

    /**
     * 列表
     * @param baseClaims
     * @return
     */
    PageInfo<User> list(BaseClaims baseClaims);


    List<Map<String,Object>> excelUserList(BaseQueryParam baseQueryParam);

    /**
     * excel导入数据
     * @param request
     * @return
     */
    String importExcel(HttpServletRequest request, String communityId);
}
