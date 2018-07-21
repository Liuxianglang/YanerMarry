package com.iyaner.yaner;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@MapperScan(value = "com.iyaner.yaner.repository")
public class YanerApplication {


    /**
     * 配置数据源
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druiDataSource(){
        DataSource source=new DruidDataSource();
        return source;
    }
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        Map<String,String> map=new HashMap<String,String>();
        map.put("loginUsername","admin");
        map.put("loginPassword","iyaner123456");
        reg.setInitParameters(map);
        return reg;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Set<String> set=new HashSet<>();
        set.add("/*");
        bean.setUrlPatterns(set);
        Map<String,String> map=new HashMap<String,String>();
        map.put("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        map.put("profileEnable","true");
        map.put("principalCookieName","USER_COOKIE");
        map.put("principalSessionName","USER_SESSION");
        bean.setInitParameters(map);
        return bean;
    }

    public static void main(String[] args) {

        SpringApplication.run(YanerApplication.class, args);

    }
}
