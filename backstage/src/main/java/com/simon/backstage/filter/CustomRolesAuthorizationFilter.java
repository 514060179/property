package com.simon.backstage.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 自定义角色拦截
 * @author fengtianying
 * @date 2018/9/7 14:17
 */
public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {


    @Override
    public boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws IOException {
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] rolesArray = (String[]) mappedValue;
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        for (String aRolesArray : rolesArray) {
            if (subject.hasRole(aRolesArray)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 拒绝处理
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            {
        return false;//打住，访问到此为止
    }
}
