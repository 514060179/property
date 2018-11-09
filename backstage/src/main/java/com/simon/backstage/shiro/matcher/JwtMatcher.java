package com.simon.backstage.shiro.matcher;


import com.simon.backstage.domain.vo.JwtAccount;
import com.simon.backstage.shiro.config.Audience;
import com.simon.backstage.util.JSONUtil;
import com.simon.backstage.util.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class JwtMatcher implements CredentialsMatcher {


    @Autowired
    private Audience audience;
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

        String jwt = (String) authenticationInfo.getCredentials();
        JwtAccount jwtAccount = null;
        try{
            jwtAccount = JwtHelper.parseJwt(jwt,audience.getBase64Secret());
        } catch(SignatureException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e){
            throw new AuthenticationException("errJwt"); // 令牌错误
        } catch(ExpiredJwtException e){
            throw new AuthenticationException("expiredJwt"); // 令牌过期
        } catch(Exception e){
            throw new AuthenticationException("errJwt");
        }
        if(null == jwtAccount){
            throw new AuthenticationException("errJwt");
        }
        return true;
    }
}
