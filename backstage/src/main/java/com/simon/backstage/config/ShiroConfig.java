package com.simon.backstage.config;

import com.simon.backstage.filter.CustomRolesAuthorizationFilter;
import com.simon.backstage.filter.JwtFilter;
import com.simon.backstage.shiro.AuthRealm;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.*;
import javax.servlet.Filter;


/**
 * @author fengtianying
 * @date 2018/9/3 14:48
 */
@Configuration
public class ShiroConfig {


    /**
     * 设置加密形式
     * @return
     */
    @Bean
    public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//加密方式要求更改可以使用 SHA-256
        hashedCredentialsMatcher.setHashIterations(2);//迭代次数，默认值是1 加密次数
        return hashedCredentialsMatcher;
    }
    /**
     * Realm配置
     * @return
     */
    @Bean
    public AuthRealm userRealm(){
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(credentialsMatcher());
        return new AuthRealm();
    }

    /**
     * SessionDAO配置
     * @return
     */
//    @Bean
//    public SessionDAO sessionDAO(){
//        return new MemorySessionDAO();
//    }

    /**
     * sessionManager配置
     * @return
     */

    /**
     * 安全管理器
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(AuthRealm authRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
//        securityManager.setSessionManager(new JwtSessionManager());
//        securityManager.setSubjectFactory(new NoSessionDefaultSubjectFactory());
        return securityManager;
    }
    @Autowired
    private Audience audience;
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
//        jwtFilter.setAudience(audience);
        filters.put("token",jwtFilter );
        filters.put("customRolesAuthorizationFilter",new CustomRolesAuthorizationFilter() );
//        filters.put("corsFilter", new RestFilter());
//        filters.put("customRolesAuthorizationFilter", new CustomRolesAuthorizationFilter());
        shiroFilter.setFilters(filters);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/test/","token");
        filterChainDefinitionMap.put("/test/t2","customRolesAuthorizationFilter[admin]");
//        filterChainDefinitionMap.putAll(roleChains());
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }

    //角色拦截
//    @Bean
//    public Map<String,String> roleChains() {
//        Map<String,String> otherChains = new HashMap<>();//规则集合
//        //获取权限
//        List<Map<String,String>> mapList = roleAndJnService().findCustomRolesAuthorization();
//        for (Map<String,String> map : mapList){
//            if (map.get("url")!=null&&!"".equals(map.get("url").trim())){
//                otherChains.put(map.get("url"),"corsFilter,token,customRolesAuthorizationFilter["+map.get("roleName")+"]");
//            }
//        }
//                String permission = "perms[" + resources.getResurl()+ "]";
//                filterChainDefinitionMap.put(resources.getResurl(),permission);
//        return otherChains;
//    }

//    @Bean
//    public  RoleAndJnService roleAndJnService(){
//        return new RoleAndJnServiceImpl();
//    }
    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     */
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }

    /**
     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
     */
//    @Bean
//    @DependsOn(value = "lifecycleBeanPostProcessor") //依赖其他bean的初始化
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        return new DefaultAdvisorAutoProxyCreator();
//    }

    /**
     * 加入注解的使用，不加入这个注解不生效 使用shiro框架提供的切面类，用于创建代理对象
     * @param securityManager
     * @return
     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }

}
