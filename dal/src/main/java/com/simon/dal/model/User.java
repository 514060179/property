package com.simon.dal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

@ApiModel(value = "User", description = "用户")
public class User {

    @ApiModelProperty(value="用户id")
    private String userId;

    @ApiModelProperty(value="社区id", example="c123456")
    @NotEmpty(message="社区id不能为空")
    private String communityId;

    @ApiModelProperty(value = "名字",example = "西蒙")
    private String name;

    @ApiModelProperty(value = "英文名字",example = "simon")
    private String englishName;

    @ApiModelProperty(value = "性别(0女1男)",example = "0")
    private Integer sex;

    @ApiModelProperty(value = "区号",example = "86")
    private String countryCode;

    @ApiModelProperty(value = "手机号",example = "13421266955")
    private String tel;

    @ApiModelProperty(value = "电邮",example = "514060179@qq.com")
    private String email;

    @ApiModelProperty(value="出生日期",example="2018-08-08")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @ApiModelProperty(value = "身份证号码",example = "441781199803043548")
    private String idCard;

    @ApiModelProperty(value = "用户登录账号",example = "simon")
    private String username;

    @ApiModelProperty(value = "用户登录密码",example="123456")
    private String password;

    @ApiModelProperty(value = "用户头像",example = "/a/b/c.jpg")
    private String portrait;

    @ApiModelProperty(value = "婚姻制度",example = "未婚")
    private String marriageSystem;

    @ApiModelProperty(value = "配偶名字",example = "nimei")
    private String mateName;

    @ApiModelProperty(value="是否删除",readOnly=true)
    private Boolean deleted;

    @ApiModelProperty(value="创建时间",readOnly=true)
    private Date createTime;

    @ApiModelProperty(value="更新时间",readOnly=true)
    private Date updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
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

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait == null ? null : portrait.trim();
    }

    public String getMarriageSystem() {
        return marriageSystem;
    }

    public void setMarriageSystem(String marriageSystem) {
        this.marriageSystem = marriageSystem == null ? null : marriageSystem.trim();
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName == null ? null : mateName.trim();
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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