package com.completablefuture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @FileName: StartApplication
 * @Description：
 * @Author: lls
 * @Created: 2024/04/25 10:20
 * @Versions: V1.0
 * @Company:
 */
@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StartApplication.class, args);
    }

}