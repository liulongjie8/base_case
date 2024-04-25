package com.base.parent.interfaceshake;

import cn.hutool.core.util.ObjectUtil;
import com.base.parent.interfaceshake.annotation.RequestKeyParam;
import com.base.parent.interfaceshake.annotation.RequestLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @FileName: RequestKeyGenerator
 * @Description：  创建参数唯一key
 * @Author: lls
 * @Created: 2024/03/29 9:46
 * @Versions: V1.0
 * @Company:
 */
public class RequestKeyGenerator {

    /**
     * 获取 LockKey
     * @param joinPoint
     * @return
     */
    public static String getLockKey(ProceedingJoinPoint joinPoint){
        //获取方法连接点的方法签名对象
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        //获取方法对象
        Method method = methodSignature.getMethod();
        //获取RequestLock注解
        RequestLock requestLock = method.getAnnotation(RequestLock.class);
        //获取方法的参数集合
        final Parameter[] parameters = method.getParameters();
        //获取连接点的参数集合
        final Object[] args = joinPoint.getArgs();
        //创建字符对象
        StringBuilder sb = new StringBuilder();

        //循环方法参数中的RequestKeyParam注解
        for(int i=0;i<parameters.length; i++){
              //获取参数上的RequestKeyParam注解
              final RequestKeyParam keyParam = parameters[i].getAnnotation(RequestKeyParam.class);
              if(ObjectUtil.isEmpty(keyParam)){
                  continue;
              }
             //如果属性是RequestKeyParam注解，则拼接 连接符 "& + RequestKeyParam"
              sb.append(requestLock.delimiter()).append(args[i]);
        }

        /**
         * 如果方法上没有
         */
        if(ObjectUtil.isEmpty(sb)){
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                final Object value = args[i];
                Field[] declaredFields = value.getClass().getDeclaredFields();
                if(ObjectUtil.isEmpty(declaredFields)){
                    continue;
                }
                for(Field field : declaredFields){
                    RequestKeyParam annotation = field.getAnnotation(RequestKeyParam.class);
                    if(ObjectUtil.isNotEmpty(annotation)){
                        field.setAccessible(true);
                        sb.append(requestLock.delimiter()).append(ReflectionUtils.getField(field, value));
                    }
                }
            }
        }
        return  requestLock.prefix() + sb;
    }

}