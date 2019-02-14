package com.simon.backstage.domain.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/2/14 9:31
 */
public class Community {

    @ApiModelProperty(value = "社区id",example = "05c63989a7344d10940410115aac6214")
    private String communityId;

    @ApiModelProperty("社区编号")
    private String communityNo;

    @ApiModelProperty("社区名字")
    private String communityName;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityNo() {
        return communityNo;
    }

    public void setCommunityNo(String communityNo) {
        this.communityNo = communityNo;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
