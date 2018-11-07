package com.simon.backstage.controller;

import com.simon.backstage.service.UserService;
import com.simon.dal.model.User;
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
    @GetMapping("t1")
    public String t1(String param){
        return "t1";
    }
    @PostMapping("t2")
    public String t2(@RequestBody String param2){
        return "t2";
    }

    @PostMapping("t3")
    public User t3(@RequestBody User user){
        return userService.findOne(1L);
    }
}
