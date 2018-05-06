package edu.scu.hereis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用来测试配置的是否正确，后面可以删除
 */
@Controller
public class TestController {

    @ResponseBody
    @GetMapping("/")
    public String Test(){

        return "success";
    }
}
