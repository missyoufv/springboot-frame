package cn.duw.frame.controller;

import cn.duw.frame.service.BucketConfigService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BucketConfigController {

    @Resource
    private BucketConfigService bucketConfigService;


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Long id) {
        bucketConfigService.deleteDataSource(id);
        return "SUCCESS";
    }
}
