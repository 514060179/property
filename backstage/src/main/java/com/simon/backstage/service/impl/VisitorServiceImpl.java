package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.VisitorMapper;
import com.simon.backstage.domain.model.Visitor;
import com.simon.backstage.service.VisitorService;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 访客
 * @author fengtianying
 * @date 2018/12/14 17:26
 */
@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorMapper visitorMapper;
    @Override
    public PageInfo<Visitor> list(BaseClaims baseClaims) {
        PageHelper.startPage(baseClaims.getPageNo(),baseClaims.getPageSize());
        return new PageInfo<>(visitorMapper.selectByCondition(baseClaims));
    }
    
	@Override
	public Visitor add(Visitor visitor) {
		visitor.setVisitorId(UUIDUtil.uidString());
		int result = visitorMapper.insertSelective(visitor);
		if(result > 0){
			return visitor;
		}
		return null;
	}
}
