package com.completablefuture.web;

import org.lls.common.ResponseResult;
import org.lls.service.OrderService;
import org.lls.service.UserService;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * @FileName: OrderController
 * @Description：
 * @Author: lls
 * @Created: 2024/04/25 10:22
 * @Versions: V1.0
 * @Company:
 */
@RestController
public class OrderController {
    @Resource
    private UserService userService;
    @Resource
    private OrderService orderService;

    @RequestMapping("/findlist/sync")
    public ResponseResult<String> sync(){
        ResponseResult result = new ResponseResult();
        StopWatch sw = new StopWatch();
        sw.start();
        userService.getUser();
        orderService.findOrders();
        sw.stop();
        System.out.println(sw.getTotalTimeSeconds());
        result.setCode("200");
        result.setData(null);
        return result;
    }

    @RequestMapping("/findlist/async")
    public ResponseResult<String> async(){
        ResponseResult result = new ResponseResult();
        StopWatch sw = new StopWatch();
        sw.start();
        CompletableFuture<Void> userFuture = CompletableFuture.runAsync(() -> {
              userService.getUser();
        });
        CompletableFuture<Void> orderFuture = CompletableFuture.runAsync(() -> {
            orderService.findOrders();
        });
        userFuture.join();
        orderFuture.join();
        sw.stop();
        System.out.println(sw.getTotalTimeSeconds());
        result.setCode("200");
        result.setData(null);
        return result;
    }




}