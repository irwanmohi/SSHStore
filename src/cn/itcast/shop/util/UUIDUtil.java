package cn.itcast.shop.util;

import java.util.UUID;

/**
 * 随机生成字符串的工具类
 */
public class UUIDUtil {

    /**
     * 获得随机字符串
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
