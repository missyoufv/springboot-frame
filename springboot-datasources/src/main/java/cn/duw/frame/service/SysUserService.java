package cn.duw.frame.service;

import cn.duw.frame.entity.SysUser;
import cn.duw.frame.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;


    public SysUser getUserInfo(Integer id) {

        return sysUserMapper.getSysUserById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception{
        sysUserMapper.delete(id);
        TimeUnit.SECONDS.sleep(180);
    }

    /**
     * 同一个记录id，多个事务修改，一个事务没有提交，会提示锁超时
     * @param sysUser
     * @throws Exception
     */
    @Transactional
    public void update(SysUser sysUser) throws Exception{
        sysUserMapper.update(sysUser);
        TimeUnit.SECONDS.sleep(180);
    }

    @Transactional
    public void insert(SysUser sysUser){
        int i = 1/0;
        sysUserMapper.insert(sysUser);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(SysUser sysUser) {
       sysUserMapper.delete(sysUser.getId());
       insert(sysUser);
    }



}
