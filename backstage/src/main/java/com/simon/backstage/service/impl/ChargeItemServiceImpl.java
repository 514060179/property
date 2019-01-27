package com.simon.backstage.service.impl;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.ChargeItemMapper;
import com.simon.backstage.domain.model.ChargeItem;
import com.simon.backstage.domain.vo.UnitWithItem;
import com.simon.backstage.service.ChargeItemService;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fengtianying
 * @date 2019/1/26 16:14
 */
@Service
public class ChargeItemServiceImpl implements ChargeItemService {

    @Autowired
    private ChargeItemMapper chargeItemMapper;
    @Override
    public ChargeItem add(ChargeItem chargeItem) {
        chargeItem.setItemId(UUIDUtil.uidString());
        if (chargeItemMapper.insertSelective(chargeItem)>0){
            return chargeItem;
        }
        return null;
    }

    @Override
    public ChargeItem detail(String itemId) {
        return chargeItemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public int upd(ChargeItem chargeItem) {
        return chargeItemMapper.updateByPrimaryKeySelective(chargeItem);
    }

    @Override
    public int del(String itemId) {
        return chargeItemMapper.deleteByPrimaryKey(itemId);
    }

    @Override
    public PageInfo<ChargeItem> list(BaseQueryParam baseQueryParam) {
        return new PageInfo<>(chargeItemMapper.selectByCondition(baseQueryParam));
    }

    @Override
    public int unitAddItem(List<UnitWithItem> unitWithItemList) {
        unitWithItemList.forEach((unitWithItem)->{
            unitWithItem.setUnitItemId(UUIDUtil.uidString());
        });
        return chargeItemMapper.unitAddItem(unitWithItemList);
    }
}
