package com.simon.backstage.util;

import cn.jiguang.common.resp.DefaultResult;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author fengtianying
 * @date 2018/12/29 14:31
 */
public class JPushUtil {
    //配置参数暂时放在这
    private static String masterSecret="de594dbb39f352e897c853bf";
    private static String appKey="cd7058db5b5f4fd24e9d7bc4";
    private static JPushClient jpushClient = new JPushClient(masterSecret, appKey);

    private static Logger logger = LoggerFactory.getLogger(JPushUtil.class);

    /**
     *  给设备添加标签
     * @param registrationId 设备号(由客户端生成)
     * @param tag
     * @return
     */
    public static boolean addDeviceTag(String registrationId, String tag) {
        try {
            Set<String> tagsToAdd = new HashSet<>();
            tagsToAdd.add(tag);
            logger.debug("给设备{}添加标签{}",registrationId,tag);
            DefaultResult defaultResult = jpushClient.updateDeviceTagAlias(registrationId,null,tagsToAdd,null);
            if (defaultResult.isResultOK()){
                logger.debug("给设备添加标签response:"+JSONUtil.objectToJson(defaultResult));
                return true;
            }
            logger.debug("添加标签返回结果===>{}",JSONUtil.objectToJson(defaultResult));
        } catch (Exception e) {
            logger.error("添加标签异常", e);
            e.printStackTrace();
            return false;
        }
        return false;
    }
    /**
     *  给设备添加别名
     * @param registrationId 设备号(由客户端生成)
     * @param alias
     * @return
     */
    public static boolean addDeviceAlias(String registrationId, String alias) {
        try {
            logger.debug("给设备{}添加别名{}",registrationId,alias);
            DefaultResult defaultResult = jpushClient.updateDeviceTagAlias(registrationId,alias,null,null);
            if (defaultResult.isResultOK()){
                logger.debug("给设备添加别名response:"+JSONUtil.objectToJson(defaultResult));
                return true;
            }
            logger.debug("添加别名返回结果===>{}",JSONUtil.objectToJson(defaultResult));
        } catch (Exception e) {
            logger.error("添加别名异常", e);
            e.printStackTrace();
            return false;
        }
        return false;
    }
    /**
     *  给设备删除别名
     * @param registrationId 设备号(由客户端生成)
     * @param alias 别名
     * @return
     */
    public static boolean delDeviceTags(String registrationId, String alias) {
        try {
            logger.debug("给设备{}删除别名{}",registrationId,alias);
            DefaultResult defaultResult = jpushClient.updateDeviceTagAlias(registrationId,true,false);
            if (defaultResult.isResultOK()){
                logger.debug("给设备删除别名response:"+JSONUtil.objectToJson(defaultResult));
                return true;
            }
            logger.debug("删除别名返回结果===>{}",JSONUtil.objectToJson(defaultResult));
        } catch (Exception e) {
            logger.error("删除别名异常", e);
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 推送信息
     * @param payload 推送消息体
     * @return
     */
    public static boolean push(PushPayload payload){
        try {
            PushResult result = jpushClient.sendPush(payload);
            if (result.isResultOK()){
                logger.debug("推送response:"+JSONUtil.objectToJson(result));
                return true;
            }
            logger.error("推送失败response:"+JSONUtil.objectToJson(result));
        } catch (Exception e) {
            logger.error("推送异常!", e);
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 创建推送消息体
     * @param msg 推送内容
     * @param product 是否生产
     * @param target 目标 (标签/别名)
     * @param map   额外数据
     * @param type 目标类型 0 别名 其他则标签
      * @return
     */
    public static PushPayload buildPushObjectByAlias(String msg, boolean product, String target, Map<String, String> map, int type) {
        AudienceTarget audienceTarget;
        if (type == 0) {
            audienceTarget = AudienceTarget.alias(target);
        } else {
            audienceTarget = AudienceTarget.tag(target);
        }
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(
                        Audience.newBuilder()
                                .addAudienceTarget(audienceTarget)
                                .build())
                .setNotification(
                        Notification
                                .newBuilder()
                                .setAlert(msg)
                                .addPlatformNotification(
                                        IosNotification.newBuilder().setSound("default")
                                                .addExtras(map).build())
                                .addPlatformNotification(
                                        AndroidNotification.newBuilder()
                                                .addExtras(map).build())
                                .build())
                .setOptions(
                        Options.newBuilder().setApnsProduction(product).build())
                .build();
    }
}
