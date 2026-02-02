package com.algowebpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("appName", "Algo Trading Platform");
        model.addAttribute("modules", new String[]{
                "Screeners",
                "Paper Trading",
                "Live Trading (FYERS)"
        });
        return "index";
    }
}
