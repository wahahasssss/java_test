package com.hdu.easyrules;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {

    private static Pattern numPattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");

    /**
     * 匹配是否包含数字
     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
     * @return
     */
    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            //异常 说明包含非数字。
//            log.error("isNumeric error:{}", e);
            return false;
        }

        // matcher是全匹配
        Matcher isNum = numPattern.matcher(bigStr);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}