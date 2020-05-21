package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Building;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.model.UserUnit;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.domain.vo.ExcelData;
import com.simon.backstage.domain.vo.ExcelUnit;
import com.simon.backstage.domain.vo.UnitQueryParam;
import com.simon.backstage.service.BuildingService;
import com.simon.backstage.service.UnitService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.ExcelUtils;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.model.User;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author fengtianying
 * @date 2018/12/10 16:43
 */
@RestController
@RequestMapping("/back/unit")
@Api(value = "UnitController", description = "单元")
public class UnitController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UnitService unitService;
    @Autowired
    private BuildingService buildingService;
    @PostMapping("add")
    @ApiOperation("添加单元")
    public ReturnMsg<Unit> add(@RequestBody Unit unit, HttpServletRequest request){
        logger.info("添加单元unit={}", JSONUtil.objectToJson(unit));
        String communityId = ClaimsUtil.getCommunityId(request);
        if (StringUtils.isEmpty(communityId)){//超级管理员
            if (StringUtils.isEmpty(unit.getCommunityId())){
                Building building = buildingService.detail(unit.getBuildingId());
                unit.setCommunityId(building.getCommunityId());
//                return ReturnMsg.fail(Code.missingParameter,"缺少社区参数communityId");
            }
        }else{
            unit.setCommunityId(communityId);
        }
        return ReturnMsg.success(unitService.add(unit));
    }

    @PostMapping("upd")
    @ApiOperation("修改单元")
    public ReturnMsg<Unit> upd(@RequestBody Unit unit){
        logger.info("修改单元unit={}", JSONUtil.objectToJson(unit));
        return ReturnMsg.success(unitService.upd(unit));
    }

    @GetMapping("del")
    @ApiOperation("删除单元")
    public ReturnMsg<Unit> del(@RequestParam String unitId){
        logger.info("删除单元unitId={}", unitId);
        return ReturnMsg.success(unitService.del(unitId));
    }

    @GetMapping("list")
    @ApiOperation("单元列表")
    public ReturnMsg<PageInfo<Unit>> list(UnitQueryParam unitQueryParam,
                                          HttpServletRequest request){
    	String communityId = ClaimsUtil.getCommunityId(request);
		if(!StringUtils.isEmpty(communityId)){
            unitQueryParam.setCommunityId(communityId);
		}
        logger.info("单元列表baseClaims={}", JSONUtil.objectToJson(unitQueryParam));
        return ReturnMsg.success(unitService.list(unitQueryParam));
    }

    @PostMapping("addUser")
    @ApiOperation("房间住户添加")
    public ReturnMsg<UserUnit> addUser(@RequestBody UserUnit userUnit){
    	logger.info("房间住户添加userUnit={}", JSONUtil.objectToJson(userUnit));
        //查询存在业主
        if (userUnit.getOwner()) {//业主
            if (Objects.isNull(unitService.findUserUnitByUnitId(userUnit.getUnitId()))){
                userUnit.setConvincing(true);
            }
        }
        return ReturnMsg.success(unitService.addUser(userUnit));
    }

//    @PostMapping("updUnitUser")
//    @ApiOperation("修改单元的用户信息")
//    public ReturnMsg updUnitUser(@RequestBody UserUnit userUnit){
//        if (userUnit.getOwner()) {//业主
//            if (!Objects.isNull(unitService.findUserUnitByUnitId(userUnit.getUnitId()))){
//                userUnit.setConvincing(false);
//            }
//        }
//        return ReturnMsg.success(unitService.updUserUnit(userUnit));
//    }

    @PostMapping("batchAddUser")
    @ApiOperation("批量房间住户绑定(只需要设定userId以及unitId)")
    public ReturnMsg batchAddUser(String userId,String unitId,@RequestBody List<UserUnit> userUnitList){

        return ReturnMsg.success(unitService.batchAddUser(userId, unitId, userUnitList));
    }
    
    @GetMapping("delUser")
    @ApiOperation("房间住户删除")
    public ReturnMsg delUser(@RequestParam String userId, @RequestParam String unitId){
    	logger.info("房间住户删除unitId={}", JSONUtil.objectToJson(unitId));
    	return ReturnMsg.success(unitService.delUser(unitId, userId));
    }

    @GetMapping("unitUserList")
    @ApiOperation("单元住户列表")
    public ReturnMsg<List<User>> unitUserList(@RequestParam String unitId){
        return ReturnMsg.success(unitService.unitUserList(unitId));
    }

    @PostMapping("import")
    @ApiOperation("导入单元")
    @ApiImplicitParam(name = "file",value = "资源文件(字节码)",paramType = "payload")
    public ReturnMsg importExcel(HttpServletRequest request,@RequestParam String buildingId) throws IOException {
        //查询社区id
        Building building = buildingService.detail(buildingId);
        if (building==null){
            return ReturnMsg.fail(Code.notfound,"找不到该建筑buildingId="+buildingId);
        }
        return ReturnMsg.success(unitService.importExcel(request,building.getCommunityId(),buildingId));
    }

    @GetMapping("export")
    @ApiOperation("导出")
    public void export(UnitQueryParam unitQueryParam, HttpServletRequest request, HttpServletResponse response) {
        String communityId = ClaimsUtil.getCommunityId(request);
        if(!StringUtils.isEmpty(communityId)){
            unitQueryParam.setCommunityId(communityId);
        }
        ExcelData data = new ExcelData();

        data.setName("單元结构导出");

        List<String> titles = new ArrayList();
        List<ExcelUnit> excelUnitList = unitService.getExcelUnits(unitQueryParam);
        //設置headder
        titles.add("社區");
        titles.add("建築");
        titles.add("單位編號");
        titles.add("單位名字");
        titles.add("單位狀態");
        titles.add("用途");
        titles.add("面積");
        titles.add("位置");
        titles.add("葉權");
        titles.add("分层建筑物相对比(百分之一)");
        titles.add("分层建筑物之子部分相对比(百分之一)");
        titles.add("全址");
        titles.add("業主/住戶");
        data.setTitles(titles);
        //获取列表
        List<List<Object>> rows = new ArrayList();
        //設置內容
        excelUnitList.forEach(excelUnit -> {
            List<Object> row = new ArrayList<>();
            row.add(excelUnit.getCommunityName());
            row.add(excelUnit.getBuildingName());
            row.add(excelUnit.getUnitNo());
            row.add(excelUnit.getUnitName());
            row.add(excelUnit.getUnitStatus());
            row.add(excelUnit.getUnitPurpose());
            row.add(excelUnit.getUnitCoveredArea());
            row.add(excelUnit.getUnitPosition());
            row.add(excelUnit.getUnitTitle());
            row.add(excelUnit.getUnitRelativeProportion());
            row.add(excelUnit.getUnitChildRelativeProportion());
            row.add(excelUnit.getUnitFullAddress());
            row.add(excelUnit.getUsers());
            rows.add(row);
        });
        data.setRows(rows);
        try {
            ExcelUtils.exportExcel(response,"bms澳門物業单位",data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
