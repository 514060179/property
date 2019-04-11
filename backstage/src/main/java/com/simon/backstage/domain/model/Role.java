package com.simon.backstage.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "Role", description = "角色")
public class Role {

    @ApiModelProperty(value = "id",example="1")
    private Long roleId;

    @ApiModelProperty(value = "角色名称",example="admin")
    private String roleName;

    @ApiModelProperty(value = "角色描述",example="超级管理员")
    private String roleDescription;

    @ApiModelProperty(value = "是否默认",example="true")
    private Boolean roleDefault;

    private List<Jurisdiction> jurisdictionList;

    public Boolean getRoleDefault() {
        return roleDefault;
    }

    public void setRoleDefault(Boolean roleDefault) {
        this.roleDefault = roleDefault;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public List<Jurisdiction> getJurisdictionList() {
        return jurisdictionList;
    }

    public void setJurisdictionList(List<Jurisdiction> jurisdictionList) {
        this.jurisdictionList = jurisdictionList;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}