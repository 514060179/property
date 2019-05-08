package com.simon.backstage.scheduled;


import cn.jpush.api.push.model.PushPayload;
import com.simon.dal.dao.UserMapper;
import com.simon.dal.model.User;
import com.simon.dal.util.JPushUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 *  生日提醒
 * @author fengtianying
 * @date 2019/2/19 9:04
 */
@Component
@EnableScheduling
public class BirthdayJob {

    @Autowired
    private UserMapper userMapper;
    @Scheduled(cron = "0 0 10 * * ?")
    public void job(){
        List<User> userList = userMapper.selectBirthdayUser();
        if (userList!=null){
            userList.forEach(user -> {
                new Thread(()->{
                    PushPayload pu = JPushUtil.buildPushObjectByAlias("管家APP祝賀你生日快樂！",true, user.getUserId(), new HashMap<>(), 0);
                    JPushUtil.push(pu);
                }).start();
            });
        }
    }
}
