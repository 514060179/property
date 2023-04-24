package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.ChargeItemMapper;
import com.simon.backstage.dao.UnitChargeItemMapper;
import com.simon.backstage.domain.model.ChargeItem;
import com.simon.backstage.domain.vo.QueryWithIdParam;
import com.simon.backstage.domain.vo.UnitItemWithUser;
import com.simon.backstage.domain.vo.UnitWithItem;
import com.simon.backstage.service.ChargeItemService;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author fengtianying
 * @date 2019/1/26 16:14
 */
@Service
public class ChargeItemServiceImpl implements ChargeItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChargeItemMapper chargeItemMapper;
    @Autowired
    private UnitChargeItemMapper unitChargeItemMapper;
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
    public ChargeItem findItemIdAndUnitId(String itemId, String unitId) {
        return chargeItemMapper.selectByItemIdAndUnitId(itemId, unitId);
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
    public int delUnitChargeItem(String unitItemId) {
        String[] ids = unitItemId.split(",");
        for (String id : ids) {
            unitChargeItemMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public PageInfo<ChargeItem> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(chargeItemMapper.selectByCondition(baseQueryParam));
    }

    @Override
    public int unitAddItem(List<UnitWithItem> unitWithItemList) {
        Iterator<UnitWithItem> it = unitWithItemList.iterator();
        while (it.hasNext()){
            UnitWithItem unitWithItem = it.next();
            QueryWithIdParam queryWithIdParam = new QueryWithIdParam();
            queryWithIdParam.setUnitId(unitWithItem.getUnitId());
            List<ChargeItem> items = chargeItemMapper.unitItemList(queryWithIdParam);
            for (ChargeItem item : items) {
                logger.info("item.getItemId()={},unitWithItemList.get(i).getItemId()={}", item.getItemId(), unitWithItem.getItemId());
                if(item.getItemId().equals(unitWithItem.getItemId())){
                    //表示已关联该收费项目
                    logger.info("移除关联收费项 item={}", JSONUtil.objectToJson(item));
                    it.remove();
                    break;
                }
            }
        }
        if(unitWithItemList.size() > 0){
            logger.info("添加绑定收费项 list={}", JSONUtil.objectToJson(unitWithItemList));
            return chargeItemMapper.unitAddItem(unitWithItemList);
        }
        return 0;
    }

    @Override
    public PageInfo<ChargeItem> unitItemList(QueryWithIdParam queryWithIdParam) {

        return new PageInfo<>(chargeItemMapper.unitItemList(queryWithIdParam));
    }

    @Override
    public List<UnitItemWithUser> cycleUnitItem() {
        return chargeItemMapper.cycleUnitItem();
    }
}
