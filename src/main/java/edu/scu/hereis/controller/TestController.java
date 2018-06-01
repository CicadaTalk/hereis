package edu.scu.hereis.controller;

import edu.scu.hereis.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来测试配置的是否正确，后面可以删除
 */
@Controller
public class TestController {

    @Autowired
    private Configuration configuration;

    @ResponseBody
    @GetMapping("/")
    public String Test(){

        return "success";
    }
}
