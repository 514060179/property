package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Visitor;
import com.simon.dal.vo.BaseClaims;

/**
 * @author fengtianying
 * @date 2018/12/14 17:26
 */
public interface VisitorService {
	/**
	 * 列表
	 * @param baseClaims
	 * @return
	 */
    PageInfo<Visitor> list(BaseClaims baseClaims);

    /**
     * 添加
     * @param visitor
     * @return
     */
    Visitor add(Visitor visitor);
    
    
}
