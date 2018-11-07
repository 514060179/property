package com.simon.app.filter;

import com.simon.app.config.Audience;
import com.simon.app.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fengtianying
 * @date 2018/11/6 15:45
 */
//@component
//@WebFilter(urlPatterns = "/test/*", filterName = "authFilter")
public class JwtFilter extends GenericFilterBean {

    @Autowired
    private Audience audience;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //等到请求头信息authorization信息
        final String authHeader = request.getHeader("Authorization");
        filterChain.doFilter(servletRequest, servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {

            if (authHeader == null || !authHeader.startsWith("bearer;")) {
//                throw new LoginException(ResultEnum.LOGIN_ERROR);
            }
            final String token = authHeader.substring(7);

            try {
                if (audience == null) {
                    BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                    audience = (Audience) factory.getBean("audience");
                }
                final Claims claims = JwtHelper.parseJWT(token, audience.getBase64Secret());
                if (claims == null) {
//                    throw new LoginException(ResultEnum.LOGIN_ERROR);
                }
//                request.setAttribute(Constants.CLAIMS, claims);
            } catch (final Exception e) {
//                throw new LoginException(ResultEnum.LOGIN_ERROR);
            }

        }
    }
}
