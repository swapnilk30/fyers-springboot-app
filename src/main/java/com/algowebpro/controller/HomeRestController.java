package com.algowebpro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeRestController {

    @RequestMapping("/welcome")
    public String welcome() {
        return "Welcome to AlgoWebPro API!";
    }



}
