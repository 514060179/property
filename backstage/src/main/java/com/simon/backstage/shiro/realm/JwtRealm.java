package com.simon.backstage.shiro.realm;

import com.simon.backstage.shiro.token.JwtToken;
import com.simon.backstage.util.JSONUtil;
import com.simon.backstage.util.JwtHelper;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class JwtRealm extends AuthorizingRealm {


    public Class<?> getAuthenticationTokenClass() {
        // 此realm只支持jwtToken
        return JwtToken.class;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String payload = (String) principalCollection.getPrimaryPrincipal();
        // likely to be json, parse it:
        if (payload.startsWith("jwt:") && payload.charAt(4) == '{'
                && payload.charAt(payload.length() - 1) == '}') {

            Map<String, Object> payloadMap = JSONUtil.jsonToObject(payload.substring(4),Map.class);
            String roleStr = (String)payloadMap.get("roles");
            Set<String> roles = new HashSet<>(Arrays.asList(roleStr.split(",")));
            SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
            if(null!=roles&&!roles.isEmpty())
                info.setRoles(roles);
//            Set<String> permissions = JsonWebTokenUtil.split((String)payloadMap.get("perms"));
//            if(null!=permissions&&!permissions.isEmpty())
//                info.setStringPermissions(permissions);
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof JwtToken)) {
            return null;
        }
        JwtToken jwtToken = (JwtToken)authenticationToken;
        String jwt = (String)jwtToken.getCredentials();
        String payload = null;
        try{
            // 预先解析Payload
            // 没有做任何的签名校验
            payload = JwtHelper.parseJwtPayload(jwt);
        } catch(MalformedJwtException e){
            throw new AuthenticationException("errJwt");     //令牌格式错误
        } catch(Exception e){
            throw new AuthenticationException("errsJwt");    //令牌无效
        }
        if(null == payload){
            throw new AuthenticationException("errJwt");    //令牌无效
        }
        return new SimpleAuthenticationInfo("jwt:"+payload,jwt,this.getName());
    }
}
