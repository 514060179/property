package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.dal.model.Visitor;
import com.simon.dal.vo.BaseClaims;

/**
 * @author fengtianying
 * @date 2018/12/14 17:26
 */
public interface VisitorService {

    PageInfo<Visitor> list(BaseClaims baseClaims);
}
