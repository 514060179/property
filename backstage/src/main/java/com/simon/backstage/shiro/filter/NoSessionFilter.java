package com.simon.backstage.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @author fengtianying
 * @date 2018/11/9 10:31
 */
public class NoSessionFilter extends DefaultWebSubjectFactory {

    private final DefaultSessionStorageEvaluator sessionStorageEvaluator;

    public NoSessionFilter(DefaultSessionStorageEvaluator sessionStorageEvaluator) {
        this.sessionStorageEvaluator = sessionStorageEvaluator;
    }
    @Override
    public Subject createSubject(SubjectContext context) {
        // 这里都不创建session
        AuthenticationToken token = context.getAuthenticationToken();
        context.setSessionCreationEnabled(Boolean.FALSE);
        this.sessionStorageEvaluator.setSessionStorageEnabled(Boolean.FALSE);
        return super.createSubject(context);
    }
}
