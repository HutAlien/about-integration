package com.alien.practise.configuration.transactionManager;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import oracle.jdbc.xa.client.OracleXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author: alien
 * @date: 2020/4/1 11:48
 * @description: jta+atomikos解决多数据源事务问题
 *
 * 支持分布式事务，可以使用Spring Boot集成 Aatomikos来解决，但是我一般不建议这样使用，因为使用分布式事务会增加请求的响应时间，
 * 影响系统的TPS。一般在实际工作中，会利用消息的补偿机制来处理分布式的事务。
 *
 */
@Configuration
public class PrimaryDataSourceConfig {

    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource(@Qualifier("primaryDataSourceProperties") DataSourceProperties properties) throws SQLException {
        OracleXADataSource oracleXADataSource=new OracleXADataSource();
        oracleXADataSource.setURL(properties.getUrl());
        oracleXADataSource.setPassword(properties.getPassword());
        oracleXADataSource.setUser(properties.getUsername());
        //将事务交给Atomikos统一管理
        AtomikosDataSourceBean dataSourceBean=new AtomikosDataSourceBean();
        dataSourceBean.setXaDataSource(oracleXADataSource);
        dataSourceBean.setUniqueResourceName("primaryDataSource");
        return dataSourceBean;
    }

    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/primary/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "primarySqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
