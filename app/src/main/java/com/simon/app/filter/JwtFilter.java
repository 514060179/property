package com.simon.app.filter;

import com.simon.app.config.Audience;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.util.JSONUtil;
import com.simon.app.util.JwtHelper;
import com.simon.dal.config.RedisService;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fengtianying
 * @date 2018/11/6 15:45
 */
public class JwtFilter extends GenericFilterBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Audience audience;
    @Autowired
    private RedisService redis;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //等到请求头信息authorization信息
        final String authHeader = request.getHeader("Authorization");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            if (authHeader == null || !authHeader.startsWith("bearer;")) {//登录失败
                noLogin(response, JSONUtil.objectToJson(new ReturnMsg(false, ReturnMsg.nologin, "未登录/Authorization参数格式有误", null)));
                logger.warn("未登录/Authorization参数格式有误Authorization="+authHeader);
                return;
            }
            final String token = authHeader.substring(7);

            try {
                if (audience == null) {
                    BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                    audience = (Audience) factory.getBean("audience");
                }
                final Claims claims = JwtHelper.parseJWT(token, audience.getBase64Secret());
                if (claims == null) {
                    noLogin(response, JSONUtil.objectToJson(new ReturnMsg(false, ReturnMsg.nologin, "未登录/jwt解析有误", null)));
                    logger.warn("未登录/jwt解析有误token="+token);
                    return;
                }
                if (redis == null) {
                    BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                    redis = (RedisService) factory.getBean("redisService");
                }
                String userId = claims.get("user_id", String.class);
                boolean hasKey = redis.hasKey(userId);
                if( !hasKey ) {
                	noLogin(response, JSONUtil.objectToJson(new ReturnMsg(false, ReturnMsg.nologin, "未登录/登陆缓存已过期，请重新登陆", null)));
                	logger.warn("未登录/登陆缓存已过期，请重新登陆");
                	return;
                }
                String obj = (String) redis.get(userId);
                if( !token.equals(obj)){
                	noLogin(response, JSONUtil.objectToJson(new ReturnMsg(false, ReturnMsg.nologin, "未登录/该账号已被其他设备登陆", null)));
                	logger.warn("未登录/该账号已被其他设备登陆");
                	return;
                }
                redis.set(userId, token, 600);
                request.setAttribute("claims", claims);
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (final Exception e) {
                noLogin(response, JSONUtil.objectToJson(new ReturnMsg(false, ReturnMsg.nologin, "未登录/系统异常", null)));
                logger.warn("未登录/系统异常token="+token,e);
                return;
            }
        }
    }

    private void noLogin(HttpServletResponse response, String text) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST,GET");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.print(text);
        } catch (IOException e) {
            pw.print("IOException");
        } finally {
            pw.flush();
            pw.close();
        }
    }
}
