package com.xmcc.utils;

import java.util.UUID;

/**
 * 此工具类用于生产商品id
 */

public class IDUtils {

    public static String createIdbyUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}