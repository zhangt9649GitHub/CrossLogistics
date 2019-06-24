package com.siruiman.crosslogistics.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 张占伟
 * @date 2019/2/19 11:52
 *
 *  获取spring 容器管理对象工具类
 *
 */
@Component
public class ContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(ContextUtils.context == null){
            ContextUtils.context  = applicationContext;
        }

    }

    public static ApplicationContext getContext() {
        return context;
    }


    //通过name获取 Bean.
    public static Object getBean(String name){
        return getContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getContext().getBean(name, clazz);
    }


}
