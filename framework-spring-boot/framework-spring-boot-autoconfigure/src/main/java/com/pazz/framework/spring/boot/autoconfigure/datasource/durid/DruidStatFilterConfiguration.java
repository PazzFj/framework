package com.pazz.framework.spring.boot.autoconfigure.datasource.durid;

import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author: 彭坚
 * @create: 2018/11/15 10:09
 * @description: The Druid stat filter configuration.
 */
@ConditionalOnProperty(name = "spring.datasource.druid.StatFilter.enabled", havingValue = "true", matchIfMissing = true)
public class DruidStatFilterConfiguration {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(DruidProperties properties) {
        DruidProperties.StatFilter config = properties.getStatFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        WebStatFilter filter = new WebStatFilter();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(config.getUrlPattern() != null ? config.getUrlPattern() : "/*");
        registrationBean.addInitParameter("exclusions", config.getExclusions() != null ? config.getExclusions() : "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        if (config.getSessionStatEnable() != null) {
            registrationBean.addInitParameter("sessionStatEnable", config.getSessionStatEnable());
        }
        if (config.getSessionStatMaxCount() != null) {
            registrationBean.addInitParameter("sessionStatMaxCount", config.getSessionStatMaxCount());
        }
        if (config.getPrincipalSessionName() != null) {
            registrationBean.addInitParameter("principalSessionName", config.getPrincipalSessionName());
        }
        if (config.getPrincipalCookieName() != null) {
            registrationBean.addInitParameter("principalCookieName", config.getPrincipalCookieName());
        }
        if (config.getProfileEnable() != null) {
            registrationBean.addInitParameter("profileEnable", config.getProfileEnable());
        }
        return registrationBean;
    }

}
