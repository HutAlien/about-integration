package com.alien.practise;

import com.alien.practise.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.RowSet;
import java.util.List;

@SpringBootTest
class PractiseDemoApplicationTests {

    @Resource(name = "primaryTemplate")
    private JdbcTemplate primaryTemplate;

    @Resource(name = "secondaryTemplate")
    private JdbcTemplate secondaryTemplate;

    @Resource(name = "primarySqlSessionTemplate")
    private SqlSessionTemplate primary;

    @Resource(name = "secondarySqlSessionTemplate")
    private SqlSessionTemplate secondary;

    @Test
    void contextLoads() {
        SqlRowSet set=primaryTemplate.queryForRowSet("select * from chief.ORDER_PROC_PACK_ELE_RELA where product_id='0'");
        System.out.println(set.getRow());

    }

    @Test
    public void primaryDataSourceTest(){
        //List list=sqlSessionTemplate.selectList("com.bonc.activity.mapper.UserMapper.test");

    }

    @Test
    @Transactional("secondaryTransactionManager")
    public void secondaryDataSourceTest(){
        //List list=secondary.selectList("report.popwin.opDetail.reportInsert");
        int count=secondary.insert("report.popwin.opDetail.reportInsert");
        int rows=primary.insert("");
       /* if (count>=1){
            throw new CustomException("测试事务");
        }*/
    }

}
