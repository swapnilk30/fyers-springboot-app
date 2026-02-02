package com.algowebpro.controller;

import com.algowebpro.util.ConfigFileReaderUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
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


        return "Welcome to Trading Platform! " ;

    }


}
