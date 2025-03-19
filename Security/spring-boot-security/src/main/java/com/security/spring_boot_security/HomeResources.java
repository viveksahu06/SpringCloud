package com.security.spring_boot_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResources {
    @GetMapping("/")
    public String sayHello(){
        return "<h1>Welcome</h1>";
    }
}
