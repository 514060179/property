package com.simon.dal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "Complain", description = "投诉/保修")
public class Complain {

    @ApiModelProperty(value="id")
    private String complainId;

    @ApiModelProperty(value="用户id",readOnly=true)
    private String userId;

    @ApiModelProperty(value="位置(XX大厦)",example="平安大厦")
    private String complainPosition;

    @ApiModelProperty(value="具体位置(停车场,商铺...)",example="A1栋2单元501")
    private String complainSpecificPosition;

    @ApiModelProperty(value="类型(土木工程，清洁，保安...)",example="土木工程")
    private String complainType;

    @ApiModelProperty(value="类型分类（供电系统，发电机...）",example="供电系统")
    private String complainClassType;

    @ApiModelProperty(value="联络人",example="冯小猪")
    private String complainLiaisonsName;

    @ApiModelProperty(value="联络人性别(0女1男)",example="1")
    private String complainLiaisonsSex;

    @ApiModelProperty(value="联络人电邮",example="120212485@gmail.com")
    private String complainLiaisonsEmail;

    @ApiModelProperty(value="状态:0发起1收到2处理中3处理完成",readOnly=true)
    private String complainStatus;

    @ApiModelProperty(value="完成时间",readOnly=true)
    private Date complainFinishTime;

    @ApiModelProperty(value="处理人",readOnly=true)
    private String complainHandler;

    @ApiModelProperty(value="描述",example="隔三差五的断电，已向物业反应多次都没有得到解决")
    private String complainDescribe;

    @ApiModelProperty(value="声音path",example="E:/XXX/投诉.mp3")
    private String complainVoice;

    @ApiModelProperty(value="创建时间",readOnly=true)
    private Date createTime;

    @ApiModelProperty(value="更新时间",readOnly=true)
    private Date updateTime;
    
    private List<Images> images;

    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId == null ? null : complainId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getComplainPosition() {
        return complainPosition;
    }

    public void setComplainPosition(String complainPosition) {
        this.complainPosition = complainPosition == null ? null : complainPosition.trim();
    }

    public String getComplainSpecificPosition() {
        return complainSpecificPosition;
    }

    public void setComplainSpecificPosition(String complainSpecificPosition) {
        this.complainSpecificPosition = complainSpecificPosition == null ? null : complainSpecificPosition.trim();
    }

    public String getComplainType() {
        return complainType;
    }

    public void setComplainType(String complainType) {
        this.complainType = complainType == null ? null : complainType.trim();
    }

    public String getComplainClassType() {
        return complainClassType;
    }

    public void setComplainClassType(String complainClassType) {
        this.complainClassType = complainClassType == null ? null : complainClassType.trim();
    }

	public String getComplainLiaisonsName() {
        return complainLiaisonsName;
    }

    public void setComplainLiaisonsName(String complainLiaisonsName) {
        this.complainLiaisonsName = complainLiaisonsName == null ? null : complainLiaisonsName.trim();
    }

    public String getComplainLiaisonsSex() {
        return complainLiaisonsSex==null?"0":complainLiaisonsSex;
    }

    public void setComplainLiaisonsSex(String complainLiaisonsSex) {
        this.complainLiaisonsSex = complainLiaisonsSex == null ? null : complainLiaisonsSex.trim();
    }

    public String getComplainLiaisonsEmail() {
        return complainLiaisonsEmail;
    }

    public void setComplainLiaisonsEmail(String complainLiaisonsEmail) {
        this.complainLiaisonsEmail = complainLiaisonsEmail == null ? null : complainLiaisonsEmail.trim();
    }

    public String getComplainStatus() {
        return complainStatus==null?"0":complainStatus;
    }

    public void setComplainStatus(String complainStatus) {
        this.complainStatus = complainStatus == null ? null : complainStatus.trim();
    }

    public Date getComplainFinishTime() {
        return complainFinishTime;
    }

    public void setComplainFinishTime(Date complainFinishTime) {
        this.complainFinishTime = complainFinishTime;
    }

    public String getComplainHandler() {
        return complainHandler;
    }

    public void setComplainHandler(String complainHandler) {
        this.complainHandler = complainHandler == null ? null : complainHandler.trim();
    }

    public String getComplainDescribe() {
        return complainDescribe;
    }

    public void setComplainDescribe(String complainDescribe) {
        this.complainDescribe = complainDescribe == null ? null : complainDescribe.trim();
    }

    public String getComplainVoice() {
        return complainVoice;
    }

    public void setComplainVoice(String complainVoice) {
        this.complainVoice = complainVoice;
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

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}
}