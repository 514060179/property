package com.simon.app.model.vo;

/**
 * @author fengtianying
 * @date 2018/12/8 17:23
 */
public class BaseQueryParam {

    private int pageNo;

    private int pageSize;

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
}
