package com.base.parent.util;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @FileName: SpringUtil
 * @Description：
 * @Author: lls
 * @Created: 2024/03/10 13:41
 * @Versions: V1.0
 * @Company:
 */
public class SpringBeanUtil {
    private static ApplicationContext applicationContext = null;
    private static Environment environment = null;

    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanUtil.applicationContext == null) {
            SpringBeanUtil.applicationContext = applicationContext;
            environment = applicationContext.getEnvironment();
            //TODO 设置后，可以做一些操作
        }
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Environment getEnvironment() {
        return environment;
    }

    //通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);

    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    public static String getString(String key) {
        return environment.getProperty(key);
    }

    public static int getInt(String key) {
        return NumberUtils.toInt(environment.getProperty(key));
    }

    public static long getLong(String key) {
        return NumberUtils.toLong(environment.getProperty(key));
    }

    public static boolean getBoolean(String key) {
        return BooleanUtils.toBoolean(environment.getProperty(key));
    }

}