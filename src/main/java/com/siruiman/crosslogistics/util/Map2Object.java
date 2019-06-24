package com.siruiman.crosslogistics.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * @author 张占伟
 * @date 2019/1/11 13:44
 * map集合转为object工具类
 */
public class Map2Object {

    /**
     * map 集合转换为object
     * @param clazz
     * @param map
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object map2Object(Class clazz, Map map) throws IllegalAccessException, InstantiationException {
        if (map == null){
             return null;
        }
        Object obj = clazz.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field filed:fields) {
            int mod = filed.getModifiers();
//            如果是该字段是静态或者被final修饰就跳过
            if (Modifier.isFinal(mod)&&Modifier.isStatic(mod)){
                continue;
            }
            filed.setAccessible(true);
            filed.set(obj,map.get(filed.getName()));
        }
        return obj;
    }
}
