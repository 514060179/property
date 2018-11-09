package com.simon.backstage.shiro.realm;


import com.simon.backstage.shiro.matcher.JwtMatcher;
import com.simon.backstage.shiro.token.JwtToken;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@Component
public class RealmManager {

    private JwtMatcher jwtMatcher;

    @Autowired
    public RealmManager(JwtMatcher jwtMatcher) {
        this.jwtMatcher = jwtMatcher;
    }

    public List<Realm> initGetRealm() {
        List<Realm> realmList = new LinkedList<>();
        // ----- jwt
        JwtRealm jwtRealm = new JwtRealm();
        jwtRealm.setCredentialsMatcher(jwtMatcher);
        jwtRealm.setAuthenticationTokenClass(JwtToken.class);
        realmList.add(jwtRealm);
        return Collections.unmodifiableList(realmList);
    }
}
