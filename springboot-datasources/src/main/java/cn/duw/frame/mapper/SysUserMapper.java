package cn.duw.frame.mapper;


import cn.duw.frame.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户dao层
 *
 * @author Sunny
 * @version 1.0
 * @className SysUserMapper
 * @date 2019-07-23 15:24
 */
@Mapper
public interface SysUserMapper {

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    SysUser getSysUserById(@Param("id") Integer id);

    void delete(Integer id);

    void update(SysUser sysUser);

    void insert(SysUser sysUser);
}
