package com.simon.backstage.controller;

import com.simon.backstage.annotation.Log;
import com.simon.backstage.annotation.OperateType;
import com.simon.backstage.domain.model.AdvanceMoney;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.AdvanceService;
import com.simon.backstage.util.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengtianying
 * @date 2019/1/26 14:19
 */
@RestController
@RequestMapping("/back/advance")
@Api(value = "AdvanceController", description = "预收账户管理")
public class AdvanceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AdvanceService advanceService;
    @PostMapping("add")
    @ApiOperation("预收")
    @Log(description = "业主预收",operateType = OperateType.add)
    public ReturnMsg<AdvanceMoney> add(@RequestBody AdvanceMoney advanceMoney){
        logger.info("添加预收记录请求参数advanceMoney={}", JSONUtil.objectToJson(advanceMoney));
        AdvanceMoney findAdvance = advanceService.findByUserId(advanceMoney.getUserId());//是否存在账户
        String advanceId = findAdvance==null?null:findAdvance.getAdvanceId();
        advanceMoney.setAdvanceId(advanceId);
        advanceService.add(advanceMoney);
        return ReturnMsg.success(advanceService.findByUserId(advanceMoney.getUserId()));
    }

}
