package com.skypiea.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-28 11:06
 */

@Controller
public class PageController {

    @RequestMapping("/client")
    public String index() {
        return "index";
    }
}
