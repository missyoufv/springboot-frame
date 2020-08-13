package cn.duw.frame.datasource;

import lombok.extern.slf4j.Slf4j;

/**
 * 保存数据源到本地线程
 */
@Slf4j
public class DataSourceContextHolder {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 设置数据源类型
     * @param dataSourceType
     */
    public static void setDbType(DataSourceType dataSourceType) {
        threadLocal.set(dataSourceType.getType());
    }

    /**
     * 取得当前数据源类型
     * @return
     */
    public static String getDbType() {
        return threadLocal.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        threadLocal.remove();
    }
}
