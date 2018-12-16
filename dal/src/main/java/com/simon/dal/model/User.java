package com.simon.dal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "User", description = "用户")
public class User {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("社区id")
    private String communityId;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("英文名字")
    private String englishName;

    @ApiModelProperty("性别(0女1男)")
    private String sex;

    @ApiModelProperty("区号")
    private String countryCode;

    @ApiModelProperty("手机号")
    private String tel;

    @ApiModelProperty("电邮")
    private String email;

    @ApiModelProperty(name="出生日期",example="2018-08-08")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @ApiModelProperty("身份证号码")
    private String idCard;

    @ApiModelProperty("用户登录账号")
    private String username;

    @ApiModelProperty("用户登录密码")
    @JsonIgnore
    private String password;

    @ApiModelProperty("用户头像")
    private String portrait;

    @ApiModelProperty("婚姻制度")
    private String marriageSystem;

    @ApiModelProperty("配偶名字")
    private String mateName;

    @ApiModelProperty(name="是否删除",hidden=true)
    private Boolean deleted;

    @ApiModelProperty(name="创建时间",hidden=true)
    private Date createTime;

    @ApiModelProperty(name="更新时间",hidden=true)
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
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