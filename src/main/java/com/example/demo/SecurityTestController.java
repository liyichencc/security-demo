package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author v_liyichen
 * @date 2020.12.25 10:42
 */

@Controller
@RequestMapping("/test")
public class SecurityTestController {

    @RequestMapping("/index")
    @ResponseBody
    public String login() {
        return "welecome";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll() {
        return "admin权限";
    }

}
