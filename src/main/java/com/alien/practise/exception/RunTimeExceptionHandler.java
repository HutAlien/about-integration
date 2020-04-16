package com.alien.practise.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: alien
 * @date: 2020/4/1 10:08
 * @description:
 */
@RestControllerAdvice
@Slf4j
public class RunTimeExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public Map CustomExceptionHandler(CustomException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 201);
        map.put("message", e.getMessage());
        return map;
    }


}
