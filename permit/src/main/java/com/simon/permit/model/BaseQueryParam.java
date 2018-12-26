package com.simon.permit.model;

/**
 * @author fengtianying
 * @date 2018/12/20 16:08
 */
public class BaseQueryParam {

    private int pageNo = 0;

    private int pageSize = 15;

    private int startPage;

    private int endPage;

    private String keyword;

    public int getStartPage() {
        return pageNo*pageSize;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return pageSize*(pageNo+1);
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

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
