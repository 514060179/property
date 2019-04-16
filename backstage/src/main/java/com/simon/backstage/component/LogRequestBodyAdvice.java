package com.simon.backstage.component;

import com.simon.backstage.annotation.Log;
import com.simon.backstage.domain.model.OperateLog;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author fengtianying
 * @date 2019/4/12 11:26
 */
@ControllerAdvice
public class LogRequestBodyAdvice implements RequestBodyAdvice {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OperateLogPool operateLogPool;
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Log log = methodParameter.getMethod().getAnnotation(Log.class);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        if (log!=null){
            OperateLog operateLog = new OperateLog();
            operateLog.setManagerId(ClaimsUtil.getManagerId(request));
            operateLog.setOperateType(log.operateType().toString());
            operateLog.setContent(log.description());
            operateLog.setParam(JSONUtil.objectToJson(o));
            operateLog.setRequestUrl(request.getRequestURI());
            operateLogPool.addLog(operateLog);
        }
        logger.info("request url={},request param ={},request body={}",request.getRequestURI(),JSONUtil.objectToJson(request.getParameterMap()),JSONUtil.objectToJson(o));
        return o;
    }

    @Nullable
    @Override
    public Object handleEmptyBody(@Nullable Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return null;
    }
}
