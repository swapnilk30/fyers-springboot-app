package com.algowebpro.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/api/home")
public class HomeRestController {

    @RequestMapping("/welcome")
    public String welcome() {

        Map<String, String> tokenMap = readAuthTokenJson();

        String accessToken = tokenMap.get("access_token");
        String auth_code = tokenMap.get("auth_code");
        String refreshToken = tokenMap.get("refresh_token");

        return "Welcome to AlgoWebPro API!"+
                "\nAccess Token: " + accessToken +
                "\nAuth Code: " + auth_code +
                "\nRefresh Token: " + refreshToken;

    }


    private Map<String, String> readAuthTokenJson() {
        try {
            Path path = Paths.get("/Users/swapnilk/Desktop/2026/python-2026/auth_tokens.json");

            String content = Files.readString(path);

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(
                    content,
                    new TypeReference<Map<String, String>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to read auth_tokens.json", e);
        }
    }

}
