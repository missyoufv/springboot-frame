package cn.duw.frame.mq.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 获取当前时间
     * @return
     */
    public static Date date() {
        return new Date();
    }

    /**
     * 根据默认格式获取当前时间字符串
     * @return
     */
    public static String formatDateTime() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);
        return  LocalDateTime.now().format(fmt);
    }


    /**
     * 根据指定格式获取当前时间字符串
     * @param pattern
     * @return
     */
    public static String formatDateTime(String pattern) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        return date.format(fmt);
    }

}
