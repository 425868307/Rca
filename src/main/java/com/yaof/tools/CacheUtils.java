package com.yaof.tools;

import com.yaof.pojo.User;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by yaof on 2020/10/12.
 */
public class CacheUtils {

    /**
     * 添加缓存的方式：
     * 1.添加缓存的依赖jar包：
     * <dependency>
     * <groupId>org.springframework.boot</groupId>
     * <artifactId>spring-boot-starter-cache</artifactId>
     * </dependency>
     * <dependency>
     * <groupId>net.sf.ehcache</groupId>
     * <artifactId>ehcache</artifactId>
     * </dependency>
     * 2.添加缓存的配置文件  ehcache.xml
     * content:
     * <ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     * xsi:noNamespaceSchemaLocation="ehcache.xsd">
     * <p>
     * <!--
     * defaultCache:默认的缓存配置信息,如果不加特殊说明,则所有对象按照此配置项处理
     * maxElementsInMemory:设置了缓存的上限,最多存储多少个记录对象
     * eternal:代表对象是否永不过期
     * timeToIdleSeconds:最大的发呆时间
     * timeToLiveSeconds:最大的存活时间
     * overflowToDisk:是否允许对象被写入到磁盘
     * -->
     * <defaultCache maxElementsInMemory="100" eternal="false" timeToIdleSeconds="86400" timeToLiveSeconds="86400" />
     * <p>
     * <!--
     * maxEntriesLocalHeap:缓存数量; timeToLiveSeconds:缓存存活时间:43200(24小时)
     * -->
     * <cache name="news_default" maxEntriesLocalHeap="100" timeToLiveSeconds="86400"  />
     * <cache name="guests" maxEntriesLocalHeap="100" timeToLiveSeconds="86400"  />
     * <p>
     * </ehcache>
     * <p>
     * 3.在需要添加缓存的方法上面添加注解：@EnableCaching
     *
     * @Cacheable( cacheNames = "news_default", key="'getList4api_'+{#p0.pageNumber+'_'+#p0.pageSize+'_'+#p0.type+'_'+#p0.orderType}")
     */

    @Cacheable(cacheNames = "news_default", key = "'getList4api_'+{#p0.pageNumber+'_'+#p0.pageSize+'_'+#p0.type+'_'+#p0.orderType}")
    public void getMyInfo(User user) {
        System.out.println(user);
    }

}
