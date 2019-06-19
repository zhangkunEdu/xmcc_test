package com.xmcc.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 把此类定义为配置类
 */
@Configuration
public class DruidConfig {


    @Bean
    @ConfigurationProperties(prefix ="spring.druid" )
    public DruidDataSource druidDataSource(){

        DruidDataSource druidDataSource = new DruidDataSource();
        //利用filter实现监测sql执行的功能
        druidDataSource.setProxyFilters(Lists.newArrayList(statFilter()));  //Lists.newArrayList()  相当于 new ArrayList


        return druidDataSource;
    }

    //利用bean注解配置（交给spring管理） 过滤数据（实现监测sql执行的功能）
     @Bean
    public StatFilter statFilter(){

        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true);
        statFilter.setSlowSqlMillis(5);
        //设置合并sql
        statFilter.setMergeSql(true);
        return statFilter;
    }

    //配置访问路径
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        //locahost:8888/sell/druid
        return new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
    }


}