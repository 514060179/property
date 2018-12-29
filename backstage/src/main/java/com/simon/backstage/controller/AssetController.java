package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Asset;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.AssetService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fengtianying
 * @date 2018/12/10 16:49
 */
@RestController
@RequestMapping("/back/asset")
@Api(value = "AssetController", description = "资源管理")
public class AssetController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AssetService assetService;
    @PostMapping("add")
    @ApiOperation("添加资源")
    public ReturnMsg<Asset> add(@RequestBody Asset asset, HttpServletRequest request){
    	asset.setCommunityId(ClaimsUtil.getCommunityId(request));
        logger.info("添加资源asset={}", JSONUtil.objectToJson(asset));
        return ReturnMsg.success(assetService.add(asset));
    }

    @PostMapping("upd")
    @ApiOperation("修改资源")
    public ReturnMsg<Asset> upd(@RequestBody Asset asset){
        logger.info("修改资源asset={}", JSONUtil.objectToJson(asset));
        return ReturnMsg.success(assetService.upd(asset));
    }

    @GetMapping("del")
    @ApiOperation("删除资源")
    public ReturnMsg<Asset> del(@RequestParam String assetId){
        logger.info("添加资源assetId={}", assetId);
        return ReturnMsg.success(assetService.del(assetId));
    }

    @GetMapping("list")
    @ApiOperation("资源列表")
    public ReturnMsg<PageInfo<Asset>> list(BaseQueryParam baseQueryParam){
        logger.info("资源列表baseQueryParam={}", JSONUtil.objectToJson(baseQueryParam));
        return ReturnMsg.success(assetService.list(baseQueryParam));
    }


}
