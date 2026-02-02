package com.algowebpro.controller;

import com.algowebpro.util.ConfigFileReaderUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/config")
public class ConfigController {

    private final ConfigFileReaderUtil configFileReaderUtil;

    public ConfigController(ConfigFileReaderUtil configFileReaderUtil) {
        this.configFileReaderUtil = configFileReaderUtil;
    }

    @GetMapping
    public Map<String, Object> getConfigAndAuth() {
        return Map.of(
                "configYaml", configFileReaderUtil.readConfigYaml(),
                "authTokens", configFileReaderUtil.readAuthTokenJson()
        );
    }
}
