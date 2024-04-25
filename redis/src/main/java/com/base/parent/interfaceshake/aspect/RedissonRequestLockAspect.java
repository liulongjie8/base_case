package com.base.parent.interfaceshake.aspect;

import cn.hutool.core.lang.Assert;
import com.redis.interfaceshake.RequestKeyGenerator;
import com.redis.interfaceshake.annotation.RequestLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @FileName: RedissonRequestLockAspect
 * @Description：
 * @Author: lls
 * @Created: 2024/03/29 10:26
 * @Versions: V1.0
 * @Company:
 */
@Aspect
@Configuration
@Order(2)
@Slf4j
public class RedissonRequestLockAspect {

    private RedissonClient redissonClient;

    @Autowired
    public RedissonRequestLockAspect(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Around("execution(public * * (..)) && @annotation(com.redis.interfaceshake.annotation.RequestLock)")
    public Object interceptor(ProceedingJoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestLock requestLock = method.getAnnotation(RequestLock.class);
        Assert.notNull(requestLock, "重复提交前缀不能为空");
        final String lockKey = RequestKeyGenerator.getLockKey(joinPoint);
        log.info("lockKey:{}", lockKey);
        RLock lock = redissonClient.getLock(lockKey);
        boolean isLocked = false;
        try {
            isLocked = lock.tryLock();
            if(!isLocked){
                throw new RuntimeException( "您的操作太快了,请稍后重试");
            }
            lock.lock(requestLock.expire(), requestLock.timeUnit());
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException( "系统异常");
            }
        }catch (Exception e){
            log.error("获取锁异常", e);
            throw e;
        }finally {
            //释放锁
            if (isLocked && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}