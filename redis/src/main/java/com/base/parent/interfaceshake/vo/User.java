package com.base.parent.interfaceshake.vo;

import com.redis.interfaceshake.annotation.RequestKeyParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @FileName: User
 * @Description：  用户测试类
 * @Author: lls
 * @Created: 2024/03/29 9:33
 * @Versions: V1.0
 * @Company:
 */
@Data
public class User implements Serializable {

    @RequestKeyParam
    private  String userName;

    private  String phone;

    private  Integer  age;

}