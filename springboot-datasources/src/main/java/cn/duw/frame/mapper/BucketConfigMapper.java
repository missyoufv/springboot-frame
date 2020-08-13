package cn.duw.frame.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BucketConfigMapper {

    /**
     * 根据id删除批量删除分桶策略
     * @param id
     * @return
     */
    int delete(Long id);

}
