package cn.duw.frame.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DataSourceProperties {

    private Map<String, HikariDataSource> dataSourceMap;

    /**
     * 默认的数据源
     **/
    private String defaultChoice;
}
