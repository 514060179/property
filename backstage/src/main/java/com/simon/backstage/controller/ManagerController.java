package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Manager;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.ManagerService;
import com.simon.backstage.service.RoleService;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author fengtianying
 * @date 2018/12/10 14:27
 */
@RestController
@RequestMapping("/back/manager")
@Api(value = "ManagerController", description = "管理员")
public class ManagerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ManagerService managerService;
    @Autowired
    private RoleService roleService;
    @PostMapping("add")
    @ApiOperation("添加管理员")
    public ReturnMsg<Manager> add(@RequestBody @Validated Manager manager){
        logger.info("添加管理员manager={}", JSONUtil.objectToJson(manager));
        String[] roleIds = new String[]{};
        if (!StringUtils.isEmpty(manager.getRoleIds())){
            roleIds = manager.getRoleIds().split(",");
        }
        if (roleIds.length<=0){
            Long roleId = roleService.findRoleByName("manager");
            roleIds = new String[]{roleId+""};
        }
        return ReturnMsg.success(managerService.add(manager,roleIds));
    }

    @PostMapping("upd")
    @ApiOperation("修改管理员")
    public ReturnMsg<Manager> upd(@RequestBody @Validated Manager manager){
        logger.info("修改管理员manager={}", JSONUtil.objectToJson(manager));
        return ReturnMsg.success(managerService.upd(manager));
    }

    @GetMapping("del")
    @ApiOperation("删除管理员")
    public ReturnMsg<Manager> del(@RequestParam String managerId){
        logger.info("删除管理员managerId={}", managerId);
        return ReturnMsg.success(managerService.del(managerId));
    }

    @GetMapping("list")
    @ApiOperation("管理员列表")
    public ReturnMsg<PageInfo<Manager>> list(BaseQueryParam baseQueryParam){
        logger.info("管理员列表baseQueryParam={}", JSONUtil.objectToJson(baseQueryParam));
        return ReturnMsg.success(managerService.list(baseQueryParam));
    }
}
