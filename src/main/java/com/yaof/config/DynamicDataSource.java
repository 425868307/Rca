package com.yaof.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 动态数据源选定
 *
 * @author Administrator
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }

    public static class DbContextHolder {
        // 注意：数据源标识保存在线程变量中，避免多线程操作数据源时互相干扰
        //放在线程变量的数据源标识，因为线程复用的关系，如果请求进入没有设置使用的数据源，但是因为这个数据源之前已经被其他任务设置了数据源，所以会使用之前使用的数据源--
        //所以每个请求进入的时候，都需要单独给其设置数据源
        private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

        public static void setDbType(String dbType) {
            contextHolder.set(dbType);
        }

        public static String getDbType() {
            String dbType = contextHolder.get();
            return dbType;
        }

        public static void clearDbType() {
            contextHolder.remove();
        }
    }

}
