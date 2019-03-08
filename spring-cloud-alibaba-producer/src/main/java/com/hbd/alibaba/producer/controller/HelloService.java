package com.hbd.alibaba.producer.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Service
public class HelloService {
      private int num;
    @Value("${server.port}")
    private String port;
    public String helloNacos( String name){
        System.out.println(num++);
        return "生产者：port："+port+"来自："+name+System.currentTimeMillis();
    }

}
