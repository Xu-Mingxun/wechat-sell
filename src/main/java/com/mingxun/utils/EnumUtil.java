package com.mingxun.utils;

import com.mingxun.enums.CodeEnum;

/**
 * @program: wechat-sell
 * @description: 枚举工具类
 * @author: xu-mingxun
 * @create: 2024-08-20 09:04
 **/
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
