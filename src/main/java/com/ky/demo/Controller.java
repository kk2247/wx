package com.ky.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
//   在输入名称为local:8080/hello可以返回hello中的内容
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello spring boot";
    }
}
