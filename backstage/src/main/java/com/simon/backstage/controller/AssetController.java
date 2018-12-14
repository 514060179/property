package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Asset;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @PostMapping("add")
    @ApiOperation("添加资源")
    public ReturnMsg<Asset> add(@RequestBody Asset asset){
        return ReturnMsg.success();
    }

    @PostMapping("upd")
    @ApiOperation("修改资源")
    public ReturnMsg<Asset> upd(@RequestBody Asset asset){
        return ReturnMsg.success();
    }

    @GetMapping("del")
    @ApiOperation("删除资源")
    public ReturnMsg<Asset> del(@RequestParam String assetId){
        return ReturnMsg.success();
    }

    @GetMapping("list")
    @ApiOperation("资源列表")
    public ReturnMsg<PageInfo<Asset>> list(BaseQueryParam baseQueryParam){
        return ReturnMsg.success();
    }


}
