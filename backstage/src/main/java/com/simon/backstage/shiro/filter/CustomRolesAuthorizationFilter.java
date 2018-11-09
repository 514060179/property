package com.simon.backstage.shiro.filter;

import com.simon.backstage.shiro.token.JwtToken;
import com.simon.backstage.util.ResponseUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
//        if (rolesArray == null || rolesArray.length == 0) {
//            return true;
//        }
//
//        for (String aRolesArray : rolesArray) {
//            if (subject.hasRole(aRolesArray)) {
//                return true;
//            }
//        }
        try {
            String token = ((HttpServletRequest)servletRequest).getHeader("Authorization");
            subject.login(new JwtToken(token));//获取权限
            //验证权限
            return true;
        } catch (AuthenticationException e) {
//            WebUtils.toHttp(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
            ResponseUtil.responseWrite("验证失败",servletResponse);
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
