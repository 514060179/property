package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/4/11 14:00
 */
@ApiModel(value = "Enclosure", description = "文件model")
public class Enclosure {

    @ApiModelProperty(value = "id", example = "s1d65a564564qweqweda521564h5")
    private String enclosureId;
    @ApiModelProperty(value = "对象id社区、楼宇、事件等model的id", example = "数量")
    private String objectId;
    @ApiModelProperty(value = "id", example = "s1d65a564564qweqweda521564h5")
    private String enclosureUrl;
    @ApiModelProperty(value = "附件对象类型0社区1楼宇2事件", example = "0")
    private Integer enclosureObjectType;
    @ApiModelProperty(value = "附件类型0 pdf文件；1花名册", example = "0")
    private Integer enclosureType;

    public String getEnclosureId() {
        return enclosureId;
    }

    public void setEnclosureId(String enclosureId) {
        this.enclosureId = enclosureId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getEnclosureUrl() {
        return enclosureUrl;
    }

    public void setEnclosureUrl(String enclosureUrl) {
        this.enclosureUrl = enclosureUrl;
    }

    public Integer getEnclosureObjectType() {
        return enclosureObjectType;
    }

    public void setEnclosureObjectType(Integer enclosureObjectType) {
        this.enclosureObjectType = enclosureObjectType;
    }

    public Integer getEnclosureType() {
        return enclosureType;
    }

    public void setEnclosureType(Integer enclosureType) {
        this.enclosureType = enclosureType;
    }
}
