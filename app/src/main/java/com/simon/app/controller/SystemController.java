package com.simon.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.VersionService;
import com.simon.dal.model.Version;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("app/system")
@Api(tags="system",description="系统设置")
public class SystemController {
	
	@Autowired
	private VersionService versionService;
	
	@PostMapping("version")
	@ApiOperation("版本信息")
	@ApiImplicitParam(name="deviceType",value="设备类型（1.ios，2.android）",paramType="query")
	public ReturnMsg version(@RequestParam String deviceType){
		Version version = new Version();
		version.setDeviceType(deviceType);
		Version result = versionService.findVersion(version);
		return ReturnMsg.success(result);
	}
}
