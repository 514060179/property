package com.simon.backstage.domain.model;

import java.util.Date;

public class Advertisement {
    private String advId;

    private String comunityId;

    private String unitId;

    private String title;

    private Integer type;

    private String url;

    private Integer residenceTime;

    private String describe;

    private Date createTime;

    private Date updateTime;

    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId == null ? null : advId.trim();
    }

    public String getComunityId() {
        return comunityId;
    }

    public void setComunityId(String comunityId) {
        this.comunityId = comunityId == null ? null : comunityId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getResidenceTime() {
        return residenceTime;
    }

    public void setResidenceTime(Integer residenceTime) {
        this.residenceTime = residenceTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}