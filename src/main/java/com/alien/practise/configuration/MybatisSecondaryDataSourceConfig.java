package com.alien.practise.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author: alien
 * @date: 2020/3/31 17:46
 * @description: 第二数据源配置 基于SqlSessionTemplate
 */
//@Configuration
////@MapperScan(basePackages = "com.alien.practise.dao.**",sqlSessionFactoryRef = "")
//public class MybatisSecondaryDataSourceConfig {
//
//    @Primary
//    @Bean("secondarySqlSessionFactory")
//    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) throws Exception {
//       SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource);
//        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
//                getResources("classpath*:mapper/secondary/**/*.xml"));
//        return sqlSessionFactory.getObject();
//    }
//
//    @Primary
//    @Bean(name = "secondaryTransactionManager")
//    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("secondaryDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Primary
//    @Bean(name = "secondarySqlSessionTemplate")
//    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
