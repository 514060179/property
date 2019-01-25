package com.simon.backstage.component;

import com.simon.backstage.domain.model.Jurisdiction;
import com.simon.backstage.service.RoleService;
import com.simon.dal.config.RedisService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * 权限url初始化
 * @author fengtianying
 * @date 2018/12/20 10:52
 */
@Component
public class JurisdictionInit implements ApplicationRunner {

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    RoleService roleService;

    @Autowired
    RedisService redisService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();  // 获取url与类和方法的对应信息
        List list = roleService.findAllJurisdiction();
        List<Jurisdiction> jurisdictionList = new ArrayList<>();
        map.forEach((k,v)->{
            PatternsRequestCondition p = k.getPatternsCondition();
            StringBuffer jnUrl = new StringBuffer();
            for (String url : p.getPatterns()) {
                jnUrl.append(url);
            }
            if (jnUrl.indexOf("back")>0&&!list.contains(jnUrl.toString())&&jnUrl.indexOf("touch")<0){
                StringBuffer jnName = new StringBuffer();
                Jurisdiction jurisdiction = new Jurisdiction();
                jurisdiction.setJnUrl(jnUrl.toString());
                Api api = v.getBeanType().getAnnotation(Api.class);
                if (api != null) {
                    jnName.append("【"+api.description()+"】");
                }
                String name = v.getMethod().getName();
                if (name != null){
                    jnName.append(name);
                }
                jurisdiction.setJnName(jnName.toString());
                jurisdictionList.add(jurisdiction);
            }
        });
        Long roleId = roleService.findRoleByName("admin");
        //初始化角色  admin 以及  manager
        if (Objects.isNull(roleId)){
            roleService.initRole();
        }
        if (jurisdictionList.size()!=0)
            roleService.addJurisdiction(jurisdictionList);
    }
}
