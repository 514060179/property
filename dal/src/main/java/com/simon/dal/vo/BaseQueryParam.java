package com.simon.dal.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2018/12/8 17:23
 */
public class BaseQueryParam {

    private int pageNo = 0;

    private int pageSize = 15;

    private String keyword;

    private String communityId;

    private String buildingId;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public static void main(String[] args) {
        String s = "停车场数据异常情况统计(每周)-20190819.xlsx";
        System.out.println(s.matches("[\\s\\S]*.xlsx"));
    }
}
