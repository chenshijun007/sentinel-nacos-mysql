package com.hbd.alibaba.producer.controller;


import com.hbd.alibaba.producer.config.NacosConfigUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCotroller {
      private int num;
    @Value("${server.port}")
    private String port;
    @Autowired
    TestService testService;
    @Autowired
    Environment environment;
    @GetMapping("/hello/{name}")
    public String helloNacos(@PathVariable("name") String name){
        System.out.println(environment.getProperty("nacos.address"));
       // return "生产者：port："+port+"来自："+name+System.currentTimeMillis();
        return  testService.hello(12);
    }

}
