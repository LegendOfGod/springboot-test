package com.example.springboot.datasource;

import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author lqb
 * @date 2021/12/20 17:01
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }

    public DynamicDataSource(DataSource defaultDataSource, Map<Object,Object> targetDataSource) {
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(targetDataSource);
        super.afterPropertiesSet();
    }
}
