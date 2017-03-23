package com.skypiea.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作者: huangwenjian
 * 描述: 控制页面跳转的controller
 * 创建时间: 2017-02-22 14:54
 */

@Controller
public class PageController {

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping("/sys")
    public String indexPage() {
        return "index";
    }
}
