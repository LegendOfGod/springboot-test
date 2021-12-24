package com.example.springboot.datasource;

/**
 * @author lqb
 * @date 2021/12/20 16:52
 */
public class DataSourceHolder {
    /**
     * InheritableThreadLocal这个是子线程可以使用父类线程的变量
     */
    private static final ThreadLocal<String> DATA_SOURCES = new InheritableThreadLocal<>();


    public static void setDataSources(String dataSource) {
        DATA_SOURCES.set(dataSource);
    }

    public static String getDataSource() {
        return DATA_SOURCES.get();
    }

    public static void clearDataSource() {
        DATA_SOURCES.remove();
    }
}
