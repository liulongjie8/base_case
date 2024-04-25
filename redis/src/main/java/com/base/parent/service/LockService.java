package com.base.parent.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @FileName: LockService
 * @Description：
 * @Author: lls
 * @Created: 2024/03/10 13:52
 * @Versions: V1.0
 * @Company:
 */
@Service
@Slf4j
public class LockService {

    public static Integer count =10;

    @Autowired
    RedissonClient redissonClient;

    /**
     * 模拟库存减少
     */
    public void  cutDownCount(){
        RLock lock = redissonClient.getLock("cut:down:count");
        try {
            Long start = System.currentTimeMillis();
            Long timeout = 500L;
            while (true){
                boolean result = lock.tryLock();
                if(count > 0 && result){
                    count=count-1;
                    Thread.sleep(10);
                    log.info("--- 计算后库存 {}", count);
                    break;
                }
                long time = System.currentTimeMillis() - start;
                if (time>=timeout) {
                    break;
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
               e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }



    public  void watchDog(){
        RLock lock = redissonClient.getLock("cut:down:count");
        try{
            lock.lock();
            Thread.sleep(1000*45);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }

    }




}