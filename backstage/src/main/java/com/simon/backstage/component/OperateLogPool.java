package com.simon.backstage.component;


import com.simon.backstage.dao.OperateLogMapper;
import com.simon.backstage.domain.model.OperateLog;
import com.simon.backstage.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author fengtianying
 * @date 2018/11/22 8:31
 */
@Component
public class OperateLogPool {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Async("operateLogExecutor")
    public void addLog(OperateLog operateLog){
        int i = operateLogMapper.insertSelective(operateLog);
        if (i<=0){
            logger.error("添加操作日志失败：operateLog={}", JSONUtil.objectToJson(operateLog));
        }
    }

}
