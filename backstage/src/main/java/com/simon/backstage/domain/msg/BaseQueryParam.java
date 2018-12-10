package com.simon.backstage.domain.msg;

/**
 * @author fengtianying
 * @date 2018/12/10 14:20
 */
public class BaseQueryParam {

    private int pageNo;

    private int pageSize;

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
