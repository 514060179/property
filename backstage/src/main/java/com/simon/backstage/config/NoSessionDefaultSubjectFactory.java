package com.simon.backstage.config;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @author fengtianying
 * @date 2018/11/8 14:14
 */
public class NoSessionDefaultSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext subjectContext) {
        AuthenticationToken token = subjectContext.getAuthenticationToken();
        if((token instanceof JWTToken)){
            // 当token为HmacToken时， 不创建 session
            subjectContext.setSessionCreationEnabled(false);
        }
        return super.createSubject(subjectContext);
    }
}
