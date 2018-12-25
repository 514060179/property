package com.simon.app.controller;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.PlaceService;
import com.simon.dal.model.Notice;
import com.simon.dal.model.Place;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * place
 * @author fengtianying
 * @date 2018/11/9 15:48
 */
@RestController
@RequestMapping("/api/place")
@Api(tags = "place", description = "场所")
public class PlaceController {

	@Autowired
	private PlaceService placeService;

    @PostMapping("list")
    @ApiOperation("场所列表")
    public ReturnMsg<PageInfo<Place>> list(BaseQueryParam baseQueryParam){

        return ReturnMsg.success(new PageInfo<>(placeService.list(baseQueryParam)));
    }
    @PostMapping("detail")
    @ApiOperation("场所详情")
    public ReturnMsg<Place> detail(@RequestParam String placeId){

        return ReturnMsg.success(placeService.findOne(placeId));
    }

}
