package com.alien.practise;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: alien
 * @date: 2020/4/1 12:50
 * @description:
 */
@SpringBootTest
public class transactionManager {

    @Resource(name = "primarySqlSessionTemplate")
    private SqlSessionTemplate primary;

    @Resource(name = "secondarySqlSessionTemplate")
    private SqlSessionTemplate secondary;

    @Test
    public void primaryDataSourceTest(){
        List list=primary.selectList("com.bonc.activity.mapper.UserMapper.test");
        System.out.println(list.size());
    }

    @Test
    @Transactional
    public void secondaryDataSourceTest(){
        int count=secondary.insert("report.popwin.opDetail.reportInsert");
        int touchCount=primary.insert("com.bonc.activity.mapper.UserMapper.test");
    }
}
