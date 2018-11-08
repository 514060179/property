package com.simon.backstage.shiro;


import com.google.common.collect.Sets;
import com.simon.backstage.config.Audience;
import com.simon.backstage.config.JWTToken;
import com.simon.backstage.service.UserService;
import com.simon.backstage.util.JwtHelper;
import com.simon.backstage.util.SaltEncryUtil;
import com.simon.dal.model.User;
import io.jsonwebtoken.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fengtianying
 * @date 2018/9/3 15:16
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private Audience audience;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    public Class<?> getAuthenticationTokenClass() {
        return JWTToken.class;//此Realm只支持JwtToken
    }

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JWTToken jwtToken = (JWTToken) authenticationToken;
        String jwt = (String) jwtToken.getPrincipal();
        Claims claims;
        try {
            claims = JwtHelper.parseJWT(jwt,audience.getBase64Secret());
//            jwtPlayload = new JwtPlayload();
//            jwtPlayload.setId(claims.getId());
//            jwtPlayload.setUserId(claims.getSubject());// 用户名
//            jwtPlayload.setIssuer(claims.getIssuer());// 签发者
//            jwtPlayload.setIssuedAt(claims.getIssuedAt());// 签发时间
//            jwtPlayload.setAudience(claims.getAudience());// 接收方
//            jwtPlayload.setRoles(claims.get("roles", String.class));// 访问主张-角色
//            jwtPlayload.setPerms(claims.get("perms", String.class));// 访问主张-权限
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("JWT 令牌过期:" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationException("JWT 令牌无效:" + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new AuthenticationException("JWT 令牌格式错误:" + e.getMessage());
        } catch (SignatureException e) {
            throw new AuthenticationException("JWT 令牌签名无效:" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("JWT 令牌参数异常:" + e.getMessage());
        } catch (Exception e) {
            throw new AuthenticationException("JWT 令牌错误:" + e.getMessage());
        }
//         如果要使token只能使用一次，此处可以过滤并缓存jwtPlayload.getId()
//        查询
        return new SimpleAuthenticationInfo(claims, jwt, getName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Claims claims = (Claims) principal.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 解析角色并设置
        Set<String> roles = Sets.newHashSet(StringUtils.split(claims.get("roles", String.class), ","));
        info.setRoles(roles);
        // 解析权限并设置
        Set<String> permissions = Sets.newHashSet(StringUtils.split(claims.get("perms", String.class), ","));
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 保存登录名
     */
    private void setSession(Object key, Object value) {
        Session session = getSession();
        System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
        if (null != session) {
            session.setAttribute(key, value);
        }
    }

    private Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException e) {

        }
        return null;
    }

    public static void main(String[] args) {
        int hashIterations1 = 2;//加密的次数
        Object salt = "simon";//盐值
        Object credentials = "123".toCharArray();//密码
        String hashAlgorithmName = "MD5";//加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
                salt, hashIterations1);
        System.out.println("加密后的值----->" + simpleHash);//ea86bb6ddec9c18cd45e762d1d3495e9
        //ea86bb6ddec9c18cd45e762d1d3495e9 c2ltb24=
        System.out.println(new SimpleHash("SHA-256", credentials, ByteSource.Util.bytes(salt), hashIterations1));//86016a1bdbac2f4e488e0a201fb31409
    }
}
