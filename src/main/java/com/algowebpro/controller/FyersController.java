package com.algowebpro.controller;

import com.algowebpro.service.FyersApiService;
import com.tts.in.model.FyersClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fyers")
public class FyersController {

    private final FyersApiService fyersApiService;

    public FyersController(
            FyersApiService fyersApiService
            ) {
        this.fyersApiService = fyersApiService;

    }


    @GetMapping("/profile")
    public String profile() {
        return fyersApiService.getProfile().toString();
    }

    @GetMapping("/funds")
    public String funds() {
        return fyersApiService.getFunds().toString();
    }
    
    @GetMapping("/holdings")
    public String holdings() {
        return fyersApiService.getHoldings().toString();
    }   
    

    @GetMapping("/tradebook")
    public String tradebook() {
        return fyersApiService.getTradeBook().toString();
    }  
    
    @GetMapping("/positions")
    public String positions() {
        return fyersApiService.getPositions().toString();
    }  
        
    
}
