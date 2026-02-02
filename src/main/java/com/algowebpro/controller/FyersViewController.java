package com.algowebpro.controller;

import com.algowebpro.service.FyersApiService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fyers")
public class FyersViewController {

    private final FyersApiService fyersApiService;

    public FyersViewController(
            FyersApiService fyersApiService
    ) {
        this.fyersApiService = fyersApiService;

    }

    @GetMapping("/profile")
    public String profile(Model model) {

        JSONObject profileResponse = fyersApiService.getProfile();

        model.addAttribute("profile", profileResponse.toMap());

        return "fyers/profile";
    }

    @GetMapping("/funds")
    public String funds(Model model) {

        JSONObject fundsResponse = fyersApiService.getFunds();
        // Extract fund_limit array
        model.addAttribute(
                "fundList",
                fundsResponse.getJSONArray("fund_limit").toList()
        );

        //model.addAttribute("funds", fundsResponse.toMap());

        return "fyers/funds";
    }

}
