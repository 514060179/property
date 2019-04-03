package com.simon.backstage.controller;

import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.RoleService;
import com.simon.backstage.shiro.realm.JwtRealm;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 刷新权限
 *
 * @author fengtianying
 * @date 2019/4/3 9:09
 */
@RestController
@RequestMapping("role")
@Api(hidden = true)
public class RoleRefreshController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    private RoleService roleService;

    @RequestMapping("refresh")
    public ReturnMsg refresh() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        Map<String,String> otherChains = new HashMap<>();//规则集合
        List<Map<String, String>> mapList = roleService.findCustomRolesAuthorization();
        for (Map<String, String> map : mapList) {
            if (map.get("url") != null && !"".equals(map.get("url").trim())) {
                otherChains.put(map.get("url"), "token,customRolesAuthorizationFilter[" + map.get("roleName") + ",admin]");
            }
        }
        filterChainDefinitionMap.putAll(otherChains);
        AbstractShiroFilter shiroFilter = null;
        try {
            shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
                    .getObject();
        } catch (Exception e) {
            logger.error("getShiroFilter from shiroFilterFactoryBean error!", e);
        }
        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                .getFilterChainResolver();
        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                .getFilterChainManager();

        // 清空老的权限控制
        manager.getFilterChains().clear();

        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        // 重新构建生成
        Map<String, String> chains = shiroFilterFactoryBean
                .getFilterChainDefinitionMap();
        for (Map.Entry<String, String> entry : chains.entrySet()) {
            String url = entry.getKey();
            String chainDefinition = entry.getValue().trim().replace(" ", "");
            manager.createChain(url, chainDefinition);
        }
        return ReturnMsg.success();
    }

}
