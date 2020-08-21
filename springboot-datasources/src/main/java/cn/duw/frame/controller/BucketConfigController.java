package cn.duw.frame.controller;

import cn.duw.frame.entity.BucketConfig;
import cn.duw.frame.service.BucketConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class BucketConfigController {

    @Resource
    private BucketConfigService bucketConfigService;


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Integer id) {
        bucketConfigService.delete(id);
        return "SUCCESS";
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public BucketConfig get(Long id) {
        return bucketConfigService.getBucketConfig(id);
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody BucketConfig bucketConfig) {
        bucketConfigService.save(bucketConfig);
    }

}
