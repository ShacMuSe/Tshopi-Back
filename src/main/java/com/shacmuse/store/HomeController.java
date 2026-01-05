package com.shacmuse.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Value("${spring.application.name}")
    private String AppName;
    @RequestMapping("/")
    public String index() {
        String viewName = getViewName();
        return viewName;
    }

    private String getViewName(){
        System.out.println("AppName: " + AppName);
        return "index.html";
    }
}
