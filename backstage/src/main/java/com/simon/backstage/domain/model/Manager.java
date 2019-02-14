package com.simon.backstage.domain.model;

import com.simon.backstage.domain.vo.Community;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@ApiModel(value = "Manager", description = "管理员")
public class Manager {

    @ApiModelProperty(value ="id")
    private String managerId;

    @ApiModelProperty(value = "社区id",example = "qwe4567saeqw")
    @NotEmpty(message = "社区id不能为空")
    private String communityId;
    
    @ApiModelProperty(value ="社区名称",readOnly=true)
    private String communityName;

    @ApiModelProperty(value = "名字",example = "simon")
    private String name;

    @ApiModelProperty(value = "性别0女1男",example = "1")
    private Integer sex;

    @ApiModelProperty(value = "类型0普通管理员1超级管理员",example = "0")
    private Integer type;

    @ApiModelProperty(value = "邮箱",example = "15487845@qq.com")
    @Email(message = "非法邮箱！")
    private String email;

    @ApiModelProperty(value = "用户名",example = "simon")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码",example = "123456")
    private String password;

    @ApiModelProperty(hidden = true)
    private Date createTime;

    @ApiModelProperty(hidden = true)
    private Date updateTime;

    @ApiModelProperty(value = "角色id集合",example = "5,6")
    private String roleIds;

    private Community community;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}