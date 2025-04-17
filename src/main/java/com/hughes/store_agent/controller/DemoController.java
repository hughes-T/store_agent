package com.hughes.store_agent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hts
 * @date 2025/4/17 14:35
 */
@RequestMapping("/com/hughes/demo")
@RestController
public class DemoController {


    @RequestMapping("/test01")
    public String test01() {
        return "hello world";
    }

}
