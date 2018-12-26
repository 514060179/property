package com.simon.permit.controller;

import com.simon.permit.model.BaseQueryParam;
import com.simon.permit.model.ReturnMsg;
import com.simon.permit.model.Role;
import com.simon.permit.model.RoleJn;
import com.simon.permit.service.RoleService;
import com.simon.permit.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @author fengtianying
 * @date 2018/12/20 15:56
 */
@Controller
@RequestMapping("permit")
public class PermitController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("list.html")
    public String list(){
        return "permit/list";
    }

    @RequestMapping("view.html")
    public String view(ModelMap modelMap, @RequestParam Long roleId){
        modelMap.put("roleId",roleId);
        modelMap.put("roleJnList",roleService.listByRoleId(roleId));
        return "permit/view";
    }

    @RequestMapping("edit.html")
    public String edit(ModelMap modelMap, Long roleId){
        if (!Objects.isNull(roleId)&&roleId!=0){
            modelMap.put("role",roleService.findRoleById(roleId));
        }
        return "permit/edit";
    }

    @RequestMapping("/jurisdiction")
    @ResponseBody
    public String jurisdiction(BaseQueryParam baseQueryParam){
        return JSONUtil.objectToJson(roleService.list(baseQueryParam));
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(String params){
        Role role = JSONUtil.jsonToObject(params,Role.class);
        return JSONUtil.objectToJson(ReturnMsg.success(roleService.add(role)));
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(BaseQueryParam baseQueryParam){
        return JSONUtil.objectToJson(roleService.list(baseQueryParam));
    }


    @RequestMapping("/roleJnAdd")
    @ResponseBody
    public String roleJnAdd(@RequestParam Long roleId,@RequestParam Long jnId){
        RoleJn roleJn = new RoleJn();
        roleJn.setRoleId(roleId);
        roleJn.setJnId(jnId);
        return JSONUtil.objectToJson(ReturnMsg.success(roleService.roleJnAdd(roleJn)));
    }

    @RequestMapping("/roleJnDel")
    @ResponseBody
    public String roleJnDel(@RequestParam Long roleJnId){
        if (roleService.roleJnDel(roleJnId)>0){
            return JSONUtil.objectToJson(ReturnMsg.success());
        }else{
            return JSONUtil.objectToJson(ReturnMsg.fail("删除失败!"));
        }
    }


}
