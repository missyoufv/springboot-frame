package cn.duw.frame.service;

import cn.duw.frame.datasource.DataSourceConfig;
import cn.duw.frame.datasource.DataSourceSelector;
import cn.duw.frame.datasource.TargetDataSource;
import cn.duw.frame.entity.BucketConfig;
import cn.duw.frame.mapper.BucketConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class BucketConfigService {

    @Resource
    private BucketConfigMapper bucketConfigMapper;


//    @DataSourceSelector(value = TargetDataSource.RECOMMEND)
//    public void deleteDataSource(Long id) {
//        log.info("开始切换数据源");
//        ((BucketConfigService)AopContext.currentProxy()).delete(id);
//        log.info("结束切换数据源");
//    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    @DataSourceSelector(value = TargetDataSource.RECOMMEND)
    public void delete(Integer id) {
        bucketConfigMapper.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW ,rollbackFor = Exception.class)
    @DataSourceSelector(value = TargetDataSource.RECOMMEND)
    public BucketConfig getBucketConfig(long id) {
        return bucketConfigMapper.query(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    @DataSourceSelector(value = TargetDataSource.RECOMMEND)
    public void save(BucketConfig bucketConfig) {
        delete(bucketConfig.getId());
        int i = 1/0;
        bucketConfigMapper.save(bucketConfig);
    }

}
