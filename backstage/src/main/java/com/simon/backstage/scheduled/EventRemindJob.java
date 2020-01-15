package com.simon.backstage.scheduled;
import com.google.common.collect.Lists;
import com.simon.backstage.domain.vo.Community;

import com.simon.backstage.dao.AssetMapper;
import com.simon.backstage.dao.EventMapper;
import com.simon.backstage.dao.ManagerMapper;
import com.simon.backstage.domain.model.Asset;
import com.simon.backstage.domain.model.Event;
import com.simon.backstage.domain.model.Manager;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;


import java.util.Date;
import java.util.List;

/**
 *  邮件提醒
 * @author fengtianying
 * @date 2019/2/19 9:04
 */

@Component
@EnableScheduling
public class EventRemindJob {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ManagerMapper managerMapper;

    @Scheduled(cron = "0 0 9 * * ?")
    public void job(){
        logger.info("======事件邮件提醒======");
        List<Asset> assetList = assetMapper.findAllRemindList();
        assetList.forEach(asset -> {
            long day = differDay(asset.getCreateTime());
            if (day%asset.getAssetMaintainRemindCycle()==0){
                //查询所有的社区的管理员的email
                BaseQueryParam baseQueryParam = new BaseQueryParam();
                baseQueryParam.setCommunityId(asset.getCommunityId());
                List<Manager> managerList = managerMapper.selectByCondition(baseQueryParam);
                managerList.forEach(manager -> {
                    new Thread(()->{
                        emialSendHtml("定期維護提醒",manager.getEmail(),asset.getAssetNo()+"/"+asset.getAssetName());
                    }).start();
                    //生成事件
                    Event event = new Event();
                    event.setEventContent(asset.getAssetName()+"定期維護");
                    event.setEventCause(asset.getAssetName()+"定期維護");
                    event.setEventSolve(asset.getAssetName()+"定期維護");
                    event.setEventId(UUIDUtil.uidString());
                    event.setReportImages(Lists.newArrayList());
                    event.setCommunityId(manager.getCommunityId());
                    event.setEventStatus("推到行政中");
                    event.setEventDate(new Date());
                    event.setEventType("2");
                    event.setEventRemindCycle(0);
                    event.setEventFinishDate(new Date());
                    event.setCreateTime(new Date());
                    event.setUpdateTime(new Date());
                    event.setAssetNo(asset.getAssetNo());
                    event.setEventChannel(0);
                    eventMapper.insertSelective(event);
                });
            }
        });
    }

    //与当前时间相差多少天数
    private long differDay(Date date){
        return (new Date().getTime()-date.getTime())/(1000*3600*24);
    }

    private void emialSendHtml(String subject,String email,String content) {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("2245702722@qq.com");
            helper.setTo(email);
            helper.setSubject(subject);//title

            StringBuffer sb = new StringBuffer();
            sb.append("<h2 text-align:center >bms物業管理系統【定期維護提醒】</h2>")
                    .append("<p style='text-align:left'>").append(content).append("</p>").append("<p style='text-align:right'>bms物業管理系統</p>");
            helper.setText(sb.toString(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }

}
