package com.base.parent.interfaceshake.annotation;
import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 *  接口防抖注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestLock {

    /**
     * 锁前缀
     * @return
     */
    String prefix()  default "system";

    /**
     * 分隔符
     * @return
     */
    String delimiter() default  "&";

    /**
     * 过期时间
     * @return
     */
    long expire() default 1;

    TimeUnit timeUnit() default TimeUnit.SECONDS;



}
