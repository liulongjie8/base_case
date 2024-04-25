package com.base.parent.lock;

import com.redis.util.SpringBeanUtil;
import org.redisson.api.RedissonClient;

/**
 * @FileName: RedissonLockUtil
 * @Description：
 * @Author: lls
 * @Created: 2024/03/10 13:36
 * @Versions: V1.0
 * @Company:
 */
public class RedissonLockUtil {

     private static RedissonClient redissonClient;

     static {
           redissonClient  = SpringBeanUtil.getBean(RedissonClient.class);
     }

     /**
      * 加锁
      * @param name
      */
     public  static void lock(String name){
          try {
               redissonClient.getLock(name).lock();
          }catch (Exception e){
               e.printStackTrace();
               throw new RuntimeException("加锁失败");
          }

     }

     /**
      *  释放锁
      * @param name
      */
     public static void unlock(String name){
          redissonClient.getLock(name).unlock();
     }


}