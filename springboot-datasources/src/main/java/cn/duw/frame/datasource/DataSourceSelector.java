package cn.duw.frame.datasource;


import java.lang.annotation.*;

/**
 * 自定义注解数据源选择器
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface DataSourceSelector {
    /**
     * 默认的是写库
     * @return
     */
    DataSourceType value() default DataSourceType.LOCAL;
}
