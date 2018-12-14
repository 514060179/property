package com.simon.backstage.controller;

import com.simon.backstage.shiro.config.Audience;
import com.simon.backstage.service.UserService;
import com.simon.dal.model.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fengtianying
 * @date 2018/11/6 16:01
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private Audience audience;

    @GetMapping("t1")
    public String t1(String param) {
        return "t1";
    }

    @PostMapping("t2")
    public String t2(@RequestBody String param2) {
        return "t2";
    }


    @PostMapping("t4")
    public User t4(@RequestBody User user) {
//        AuthenticationToken token = new UsernamePasswordToken(user.getName(), SaltEncryUtil.getMD5SaltString(user.getName(), "123456"));
//        SecurityUtils.getSubject().logout();
//        try {
////            SecurityUtils.getSubject().login(token);//验证角色和权限
//        } catch (IncorrectCredentialsException e1) {
//            e1.printStackTrace();
//        } catch (AuthenticationException e2) {
//            e2.printStackTrace();
//        }
//        String jwt = JwtHelper.issueJwt(UUID.randomUUID().toString(), audience.getClientId(), audience.getName(), 100000L, "", "", audience.getBase64Secret());
//        System.out.println(jwt);

        return null;
    }
}
