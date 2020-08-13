package cn.duw.frame.service;

import cn.duw.frame.datasource.DataSourceSelector;
import cn.duw.frame.datasource.DataSourceType;
import cn.duw.frame.mapper.BucketConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class BucketConfigService {

    @Resource
    private BucketConfigMapper bucketConfigMapper;


    @DataSourceSelector(value = DataSourceType.TEST)
    public void deleteDataSource(Long id) {
        log.info("开始切换数据源");
        ((BucketConfigService)AopContext.currentProxy()).delete(id);
        log.info("结束切换数据源");
    }

    @Transactional
    public void delete(Long id) {
        bucketConfigMapper.delete(id);
    }
}
