package com.alien.practise.controller;

import com.alien.practise.exception.CustomException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: alien
 * @date: 2020/4/1 13:59
 * @description:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource(name = "primarySqlSessionTemplate")
    private SqlSessionTemplate primary;

    @Resource(name = "secondarySqlSessionTemplate")
    private SqlSessionTemplate secondary;

    @GetMapping
    @Transactional
    public Map test() {
        int count = secondary.insert("report.popwin.opDetail.reportInsert");
        int touchCount = primary.insert("com.bonc.activity.mapper.UserMapper.test");
        if (count == touchCount) {
            throw new CustomException("测试多数据源回滚");
        }
        Map map = new HashMap();
        map.put("code", 200);
        map.put("message", "SUCCESS");
        return map;
    }

}
