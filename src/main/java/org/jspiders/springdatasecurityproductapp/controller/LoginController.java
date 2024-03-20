package org.jspiders.springdatasecurityproductapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/access-denied")
    public String getAccessDenied()
    {
        return "access-denied";
    }
}
