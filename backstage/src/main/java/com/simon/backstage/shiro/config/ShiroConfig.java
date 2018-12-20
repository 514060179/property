package com.simon.backstage.shiro.config;

import com.simon.backstage.service.RoleService;
import com.simon.backstage.shiro.filter.CustomRolesAuthorizationFilter;
import com.simon.backstage.shiro.filter.JwtFilter;
import com.simon.backstage.shiro.filter.NoSessionFilter;
import com.simon.backstage.shiro.realm.RealmManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import javax.servlet.Filter;


/**
 * @author fengtianying
 * @date 2018/9/3 14:48
 */
@Configuration
public class ShiroConfig {

    /**
     * 安全管理器
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(RealmManager realmManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        DefaultSubjectDAO subjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = (DefaultSessionStorageEvaluator) subjectDAO.getSessionStorageEvaluator();
        NoSessionFilter subjectFactory = new NoSessionFilter(evaluator);
        securityManager.setSubjectFactory(subjectFactory);
        securityManager.setRealms(realmManager.initGetRealm());
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }
    /**
     * shiroFilter配置
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        JwtFilter jwtFilter = new JwtFilter();
        filters.put("token",jwtFilter );
        filters.put("customRolesAuthorizationFilter",new CustomRolesAuthorizationFilter() );
//        filters.put("corsFilter", new RestFilter());
//        filters.put("customRolesAuthorizationFilter", new CustomRolesAuthorizationFilter());
        shiroFilter.setFilters(filters);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        filterChainDefinitionMap.put("/back/*","token");
//        filterChainDefinitionMap.put("/test/t2","customRolesAuthorizationFilter[admin]");
        filterChainDefinitionMap.putAll(roleChains());
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }

    //角色拦截
    @Bean
    public Map<String,String> roleChains() {
        Map<String,String> otherChains = new HashMap<>();//规则集合
        //获取权限
        List<Map<String,String>> mapList = roleService().findCustomRolesAuthorization();
        for (Map<String,String> map : mapList){
            if (map.get("url")!=null&&!"".equals(map.get("url").trim())){
                otherChains.put(map.get("url"),"token,customRolesAuthorizationFilter["+map.get("roleName")+"]");
            }
        }
//                String permission = "perms[" + resources.getResurl()+ "]";
//                filterChainDefinitionMap.put(resources.getResurl(),permission);
        return otherChains;
    }

    @Bean
    public RoleService roleService(){
        return new RoleService();
    }

}
