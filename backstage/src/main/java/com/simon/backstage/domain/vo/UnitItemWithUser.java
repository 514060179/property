package com.simon.backstage.domain.vo;

import com.simon.backstage.domain.model.ChargeItem;
import com.simon.backstage.domain.model.Unit;

/**
 * @author fengtianying
 * @date 2019/2/19 9:54
 */
public class UnitItemWithUser {

    private String userId;

    private String unitItemId;

    private ChargeItem chargeItem;

    private Unit unit;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUnitItemId() {
        return unitItemId;
    }

    public void setUnitItemId(String unitItemId) {
        this.unitItemId = unitItemId;
    }

    public ChargeItem getChargeItem() {
        return chargeItem;
    }

    public void setChargeItem(ChargeItem chargeItem) {
        this.chargeItem = chargeItem;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
