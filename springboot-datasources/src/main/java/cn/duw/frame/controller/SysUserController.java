package cn.duw.frame.controller;

import cn.duw.frame.entity.SysUser;
import cn.duw.frame.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("/user/getUserInfo")
    public SysUser getUserInfo(Integer id) {
        return sysUserService.getUserInfo(id);
    }


    @GetMapping("/user/delete")
    public String deleteUser(Integer id)throws Exception {

        sysUserService.delete(id);
        return "SUCCESS";
    }

    @PostMapping("/user/update")
    public String deleteUser(@RequestBody SysUser sysUser)throws Exception {
        sysUserService.update(sysUser);
        return "SUCCESS";
    }


    @PostMapping("/user/save")
    public String save(@RequestBody SysUser sysUser)throws Exception {
        sysUserService.save(sysUser);
        return "SUCCESS";
    }
}
