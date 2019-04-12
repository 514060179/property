package com.simon.backstage.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author fengtianying
 * @date 2019/4/12 9:48
 */
@Target(ElementType.METHOD)
@Retention(RUNTIME)
@Documented
public @interface Log {

    /**
     * 方法描述
     *
     * @return ""
     */
    String description() default "";

    /**
     * 方法类型
     *
     * @return ""
     */
    OperateType operateType() default OperateType.query;

}
