package com.simon.dal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fengtianying
 * @date 2019/1/12 9:51
 */
@ApiModel(value = "ImagesUrl", description = "上传图片路径")
public class ImagesUrl {

    @ApiModelProperty(value = "原始图",example = "/images/swimming/123.png,/images/swimming/456.png")
    private String originalUrl;

    @ApiModelProperty(value = "缩略图",example = "/images/swimming/123-thumbnail.png,/images/swimming/456-thumbnail.png")
    private String thumbnailUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
