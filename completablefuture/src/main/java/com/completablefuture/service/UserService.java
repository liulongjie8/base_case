package com.completablefuture.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @FileName: UserService
 * @Description：
 * @Author: lls
 * @Created: 2024/04/25 10:33
 * @Versions: V1.0
 * @Company:
 */
@Service
@Slf4j
public class UserService {

    public void getUser(){
        try {
             Thread.sleep(1000*3);
        }catch (Exception e){
             log.error(e.getMessage(),e);
        }
    }

}