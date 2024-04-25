package com.completablefuture.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ResponseResult
 * @Description：api统一响应体
 * @Author: hlly
 * @Date: 2019/5/30 14:50
 * @Version: 1.0
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = -7041378341503655096L;
    private String code;
    private String message;
    private T data;
}