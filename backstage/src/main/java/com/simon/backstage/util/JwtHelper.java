package com.simon.backstage.util;

import com.simon.backstage.domain.vo.JwtAccount;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultHeader;
import io.jsonwebtoken.impl.DefaultJwsHeader;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.compression.CompressionCodecs;
import io.jsonwebtoken.impl.compression.DefaultCompressionCodecResolver;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.util.Strings;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author fengtianying
 * @date 2018/11/6 15:47
 */

public class JwtHelper {

    /**
     * 解析jwt
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                .parseClaimsJws(jsonWebToken).getBody();
        claims.getSubject();
        return claims;
    }
    /**
     * @param id          令牌ID
     * @param subject     用户ID
     * @param issuer      签发人
     * @param period      有效时间(毫秒)
     * @param roles       访问主张-角色
     * @param permissions 访问主张-权限
     * @param base64Security 秘钥
     * @return json web token
     */
    public static String issueJwt(String id, String subject, String issuer, Long period, String roles
            , String permissions,String base64Security) {
        long currentTimeMillis = System.currentTimeMillis();// 当前时间戳
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(base64Security);// 秘钥
        JwtBuilder jwt = Jwts.builder();
        if (Strings.isNotBlank(id)) {
            jwt.setId(id);
        }
        jwt.setSubject(subject);// 用户名主题
        if (Strings.isNotBlank(issuer)) {
            jwt.setIssuer(issuer);//签发者
        }
        if (Strings.isNotBlank(issuer)) {
            jwt.setIssuer(issuer);//签发者
        }
        jwt.setIssuedAt(new Date(currentTimeMillis));//签发时间
        if (null != period) {
            Date expiration = new Date(currentTimeMillis + period);
            jwt.setExpiration(expiration);//有效时间
        }
        if (Strings.isNotBlank(roles)){
            jwt.claim("roles", roles);//角色
        }
        if (Strings.isNotBlank(permissions)){
            jwt.claim("perms", permissions);//权限
        }
        jwt.compressWith(CompressionCodecs.DEFLATE);//压缩，可选GZIP
        jwt.signWith(SignatureAlgorithm.HS256, secretKeyBytes);//加密设置
        return jwt.compact();
    }

    /**
     *  解析JWT的Payload
     */
    public static String parseJwtPayload(String jwt){
        Assert.hasText(jwt, "JWT String argument cannot be null or empty.");
        String base64UrlEncodedHeader = null;
        String base64UrlEncodedPayload = null;
        String base64UrlEncodedDigest = null;
        int delimiterCount = 0;
        StringBuilder sb = new StringBuilder(128);
        for (char c : jwt.toCharArray()) {
            if (c == '.') {
                CharSequence tokenSeq = io.jsonwebtoken.lang.Strings.clean(sb.toString());
                String token = tokenSeq!=null?tokenSeq.toString():null;

                if (delimiterCount == 0) {
                    base64UrlEncodedHeader = token;
                } else if (delimiterCount == 1) {
                    base64UrlEncodedPayload = token;
                }

                delimiterCount++;
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        if (delimiterCount != 2) {
            String msg = "JWT strings must contain exactly 2 period characters. Found: " + delimiterCount;
            throw new MalformedJwtException(msg);
        }
        if (sb.length() > 0) {
            base64UrlEncodedDigest = sb.toString();
        }
        if (base64UrlEncodedPayload == null) {
            throw new MalformedJwtException("JWT string '" + jwt + "' is missing a body/payload.");
        }
        // =============== Header =================
        Header header = null;
        CompressionCodec compressionCodec = null;
        CompressionCodecResolver codecResolver = new DefaultCompressionCodecResolver();
        if (base64UrlEncodedHeader != null) {
            String origValue = TextCodec.BASE64URL.decodeToString(base64UrlEncodedHeader);
            Map<String, Object> m = JSONUtil.readValue(origValue);
            if (base64UrlEncodedDigest != null) {
                header = new DefaultJwsHeader(m);
            } else {
                header = new DefaultHeader(m);
            }
            compressionCodec = codecResolver.resolveCompressionCodec(header);
        }
        // =============== Body =================
        String payload;
        if (compressionCodec != null) {
            byte[] decompressed = compressionCodec.decompress(TextCodec.BASE64URL.decode(base64UrlEncodedPayload));
            payload = new String(decompressed, io.jsonwebtoken.lang.Strings.UTF_8);
        } else {
            payload = TextCodec.BASE64URL.decodeToString(base64UrlEncodedPayload);
        }
        return payload;
    }

    public static JwtAccount parseJwt(String jwt, String appKey) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        Claims claims = parseJWT(jwt,appKey);
        JwtAccount jwtAccount = new JwtAccount();
        jwtAccount.setTokenId(claims.getId());// 令牌ID
        jwtAccount.setAppId(claims.getSubject());// 客户标识
        jwtAccount.setIssuer(claims.getIssuer());// 签发者
        jwtAccount.setIssuedAt(claims.getIssuedAt());// 签发时间
        jwtAccount.setAudience(claims.getAudience());// 接收方
        jwtAccount.setRoles(claims.get("roles", String.class));// 访问主张-角色
        jwtAccount.setPerms(claims.get("perms", String.class));// 访问主张-权限
        return jwtAccount;
    }
    public static void main(String[] args) {
        String key = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
        String token = JwtHelper.issueJwt(UUID.randomUUID().toString(), "123456", "restapiuser", 3600*1000L, "1", "2", key);
//        String token = "eyJhbGciOiJIUzI1NiIsImNhbGciOiJERUYifQ.eNo8y0EKwyAQheG7zDqCo2bU3EadEVJaGjSBQujdq5vuHj_fu-Fx7rABr6IxeFIhUlFOW1RJo1OJMdZQmHP2sEC_8sA6hkq5sCODbL0ticVJsIaMz67SgHvvAzbpZzr2q0ubLZ2w4eqQyFC0C8jn-Ac_Q3s_Zf5w6EPaa24D3x8AAAD__w.0uDD4CKv7d-5VCo075s5D1Nlu7VcXhbkdwFRpieOv28.eNo8yk0KhDAMQOG7ZG0hGdMavU0rqVTwh4mCIHP3qRuX7-PdMB8FBgiIMXmfXd8zO87SuSTKLkpqI46q2Qs0YGeq82n6RapZzGpaWbY16zo9Eg8YyDOF8EGhBvTaXyCh3x8AAP__.UWBe3Yfl6Atqmmp3yb_xXhZWdJB1md0D2Tog4OYSWZE";
        System.out.println(token);
        System.out.println(parseJwtPayload(token));
        System.out.println(parseJWT(token,key));
    }
}
