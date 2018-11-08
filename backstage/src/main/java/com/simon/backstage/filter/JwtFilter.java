package com.simon.backstage.filter;

import com.simon.backstage.config.Audience;
import com.simon.backstage.config.JWTToken;
import com.simon.backstage.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fengtianying
 * @date 2018/11/6 15:45
 */
public class JwtFilter extends AccessControlFilter {

    private static final Logger log = LoggerFactory.getLogger(AccessControlFilter.class);

    public static final String DEFAULT_JWT_PARAM = "Authorization";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (null != getSubject(request, response)
                && getSubject(request, response).isAuthenticated()) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if(isJwtSubmission(request)){
            AuthenticationToken token = createToken(request, response);
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);
                return true;
            } catch (AuthenticationException e) {
                log.error(e.getMessage(),e);
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
            }
        }
        return false;
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String jwt = ((HttpServletRequest)request).getHeader(DEFAULT_JWT_PARAM);
        String host = request.getRemoteHost();
        log.info("authenticate jwt token:"+jwt);
        System.out.println("jwt:"+jwt);
        return new JWTToken(jwt);
    }

    protected boolean isJwtSubmission(ServletRequest request) {
        String jwt = ((HttpServletRequest)request).getHeader(DEFAULT_JWT_PARAM);
        return (request instanceof HttpServletRequest)
                && !StringUtils.isEmpty(jwt);
    }

}