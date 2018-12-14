package com.simon.dal.vo;

/**
 * @author fengtianying
 * @date 2018/12/8 17:23
 */
public class BaseQueryParam {

    private int pageNo = 0;

    private int pageSize = 15;

    private String keyword;

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
}
