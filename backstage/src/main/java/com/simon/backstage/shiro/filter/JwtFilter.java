package com.simon.backstage.shiro.filter;

import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.shiro.token.JwtToken;
import com.simon.backstage.util.JSONUtil;
import com.simon.backstage.util.ResponseUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                ResponseUtil.responseWrite(JSONUtil.objectToJson(ReturnMsg.fail(Code.unauthorized,"该账户未授权！")),response);
//                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
            }
        }
        return false;
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String jwt = ((HttpServletRequest)request).getHeader(DEFAULT_JWT_PARAM);
        String host = request.getRemoteHost();
        log.info("authenticate jwt token:"+jwt);
        System.out.println("jwt:"+jwt);
        return new JwtToken(jwt);
    }

    protected boolean isJwtSubmission(ServletRequest request) {
        String jwt = ((HttpServletRequest)request).getHeader(DEFAULT_JWT_PARAM);
        return (request instanceof HttpServletRequest)
                && !StringUtils.isEmpty(jwt);
    }

}