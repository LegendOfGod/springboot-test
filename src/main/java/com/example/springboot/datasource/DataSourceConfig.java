package com.example.springboot.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.springboot.anno.SwitchSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lqb
 * @date 2021/12/20 17:40
 */
@Configuration
@MapperScan(basePackages = {"com.example.springboot.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfig {

    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource defaultDataSource() {
        return new DruidDataSource();
    }

    /**
     * 向IOC容器中注入另外一个数据源
     * 全局配置文件中前缀是spring.datasource.his
     */
    @Bean(name = SwitchSource.DEFAULT_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.his")
    public DataSource hisDataSource() {
        return new DruidDataSource();
    }

    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(DataSource dataSource, DataSource hisDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("hisDataSource", hisDataSource);
        return new DynamicDataSource(dataSource, targetDataSources);
    }

    @Primary
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setDefaultFetchSize(100);
        configuration.setDefaultStatementTimeout(30);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    @Primary
    @Bean(value = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager(DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("sqlSessionTemplate")
    public SqlSessionTemplate sqlTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
