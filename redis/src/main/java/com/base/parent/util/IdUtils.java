package com.base.parent.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @FileName: IdUtils
 * @Description：
 * @Author: lls
 * @Created: 2024/03/11 9:12
 * @Versions: V1.0
 * @Company:
 */
@Slf4j
@Component
public class IdUtils {

    /**
     * redisson 客户端
     */
    private static RedissonClient redissonClient;

    /**
     * 系统前缀
     */
    public static final String SERVER_PREFIX="DEM_";


    /**
     * 时间搓 时分秒
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Autowired
    public IdUtils(RedissonClient redissonClient) {
        IdUtils.redissonClient = redissonClient;
    }

    /**
     *  获取下一个ID
     * @param prefix 业务前缀
     * @return
     */
    public static String nextId(String prefix){
        prefix =  prefix!=null ?  prefix.trim() : "LLS";
        String key = SERVER_PREFIX.concat(prefix);
        String now = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        prefix = prefix.concat(now);
        // 通过redis的自增获取序号
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        // 设置过期时间为1天
        atomicLong.expire(Duration.ofDays(1));
        //获取自增数据
        long num = atomicLong.incrementAndGet();
        return  String.format(prefix.concat("%05d"), num);
    }

}