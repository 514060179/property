package com.simon.backstage.service;

import com.simon.backstage.domain.model.AdvanceMoney;

/**
 * @author fengtianying
 * @date 2019/1/26 14:28
 */
public interface AdvanceService {

    /**
     * 根据用户id获取账户
     * @param userId
     * @return
     */
    AdvanceMoney findByUserId(String userId);

    /**
     * 添加预收记录
     * @param advanceMoney
     * @return
     */
    int add(AdvanceMoney advanceMoney);

//    /**
//     * 创建预收账户
//     * @param advanceMoney
//     * @return
//     */
//    int create(AdvanceMoney advanceMoney);
}
