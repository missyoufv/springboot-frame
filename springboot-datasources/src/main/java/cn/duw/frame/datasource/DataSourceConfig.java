package cn.duw.frame.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DataSourceConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;


    /**
     * 构造dataSource
     *
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        //读取配置文件并构造目标数据源并存放到map中
        Map<Object, Object> targetDataSourceMap = new HashMap<>(16);
        Map<String, HikariDataSource> dataSourceMap = dataSourceProperties.getDataSourceMap();

        //多数据源配置为空直接退出
        if (CollectionUtils.isEmpty(dataSourceMap)) {
            log.error("data source map is empty, project start error!");
            System.exit(0);
        }
        HikariDataSource defaultDataSource = dataSourceMap.get(dataSourceProperties.getDefaultChoice());

        dataSourceMap.forEach((key, value) -> targetDataSourceMap.put(value.getPoolName(), value));

        DynamicDataSource customDynamicDataSource = new DynamicDataSource();
        customDynamicDataSource.setTargetDataSources(targetDataSourceMap);
        if (null != defaultDataSource) {
            customDynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        } else {
            log.error("Can't find default data source, project will be exit!");
            System.exit(0);
        }
        return customDynamicDataSource;
    }


    /**
     * 事务管理
     *
     * @return 事务管理实例
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
