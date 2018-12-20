package com.simon.backstage.shiro.filter;

import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.domain.vo.JwtAccount;
import com.simon.backstage.shiro.token.JwtToken;
import com.simon.backstage.util.JSONUtil;
import com.simon.backstage.util.JwtHelper;
import com.simon.backstage.util.ResponseUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义角色拦截
 * @author fengtianying
 * @date 2018/9/7 14:17
 */
public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {


    Logger logger = LoggerFactory.getLogger(CustomRolesAuthorizationFilter.class);
    @Override
    public boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws IOException {
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] rolesArray = (String[]) mappedValue;
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        try {
            //验证权限
            for (String aRolesArray : rolesArray) {
                if (subject.hasRole(aRolesArray)) {
                    return true;
                }
            }
            return false;
        } catch (AuthenticationException e) {
//            WebUtils.toHttp(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
            logger.error("权限验证失败！",e);
            ResponseUtil.responseWrite(JSONUtil.objectToJson(ReturnMsg.fail(Code.unauthorized,"该账户未授权！")),servletResponse);
        }
        return false;
    }
    /**
     * 拒绝处理
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) {
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        response.setContentType("application/json;charset=UTF-8");
//        response.setHeader("Pragma", "No-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods", "POST,GET");
//        response.setDateHeader("Expires", 0);
//        PrintWriter pw = null;
//        try {
//            pw = response.getWriter();
//            pw.print(JSONUtil.objectToJson(ReturnMsg.fail(Code.unauthorized,"该账户未授权！")));
//        } catch (IOException e) {
//            pw.print("IOException");
//        } finally {
//            pw.flush();
//            pw.close();
//        }
        ResponseUtil.responseWrite(JSONUtil.objectToJson(ReturnMsg.fail(Code.unauthorized,"该账户未授权！")),servletResponse);
        return false;//打住，访问到此为止
    }
}
