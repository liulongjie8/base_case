package com.base.parent.interfaceshake.controller;

import com.redis.interfaceshake.annotation.RequestLock;
import com.redis.interfaceshake.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName: UserManagerController
 * @Description：
 * @Author: lls
 * @Created: 2024/03/29 9:34
 * @Versions: V1.0
 * @Company:
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserManagerController {

    @PostMapping("/add")
    @RequestLock
    public void add(@RequestBody User user){
        log.info("user:{}",user);
    }

}