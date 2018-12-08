package com.simon.app.service;

import com.simon.app.model.vo.BaseQueryParam;
import com.simon.dal.model.Community;

import java.util.List;

/**
 * @author fengtianying
 * @date 2018/12/8 16:42
 */
public interface CommunityService  {

    /**
     * 获取社区列表
     * @return
     */
    List<Community> list(BaseQueryParam baseQueryParam);
}
