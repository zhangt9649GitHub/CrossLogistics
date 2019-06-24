package com.siruiman.crosslogistics.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.siruiman.crosslogistics.config.LockConfig;
import com.siruiman.crosslogistics.mqttclient.MqttConnection;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author 张占伟
 * @date 2019/1/16 10:58
 * shiro配置类
 */
@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory
            .getLogger(ShiroConfig.class);


    // 下面两个方法对 注解权限起作用有很大的关系，
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    @Bean()
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//      设置自己定义的过滤器
        Map<String,Filter> filters = new HashMap<String,Filter>(16);
        shiroFilterFactoryBean.setFilters(filters);

        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/admin/index/login");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/noAuthority/index");
        //登录成功跳转的页面
        shiroFilterFactoryBean.setSuccessUrl("/admin/index/index");
        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //游客，开放权限
        filterChainDefinitionMap.put("/admin/index/index","authc");
        filterChainDefinitionMap.put("/admin/noAuthority/index","anon");
//        静态资源放行
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/admin/copybookSettings/open","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager( CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     */
    @Bean
    public CustomRealm getCustomRealm() {
        return new CustomRealm();
    }


    /**
     * 前端按钮控制必须
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

    /**
     * 由spring管理
     * @return
     */
    @Bean
    public MqttConnection getMqttConnection(){
        if(!LockConfig.STATE){
            return null;
        }
        return MqttConnection.getInstance();
    }

   /**
     *  mqtt 客户端
     * @param mqttConnection
     * @return
    */
    @Bean
    public MqttClient getMqttClient(MqttConnection mqttConnection){
        if(!LockConfig.STATE){
            return null;
        }
        return mqttConnection.getClient();
    }
}
