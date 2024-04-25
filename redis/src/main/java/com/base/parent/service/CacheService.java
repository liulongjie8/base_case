package com.base.parent.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Duration;

/**
 * @FileName: cachesave
 * @Description：
 * @Author: lls
 * @Created: 2024/03/10 11:29
 * @Versions: V1.0
 * @Company:
 */
@Service
@Slf4j
public class CacheService {

    @Resource
    private RedissonClient redissonClient;

    public void  saveCache() {
        RMap<Object, Object> person = redissonClient.getMap("person");
        person.put("1", "张三");
        person.put("2", "李四");
        //10秒过期时间
        boolean expire = person.expire(Duration.ofSeconds(1000));
        if(expire){
            log.info("缓存保存成功");
        }
    }

    public void getCache(){
        RMap<Object, Object> person = redissonClient.getMap("person");
        if(ObjectUtils.isEmpty(person)){
            throw  new RuntimeException("缓存不存在");
        }
        String value = (String) person.get("1");
        log.info("缓存获取结果：{}",value);
    }

}