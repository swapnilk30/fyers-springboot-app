package com.algowebpro.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Component
public class ConfigFileReaderUtil {

    @Value("${algo.config.yaml.path}")
    private String configYamlPath;

    @Value("${algo.auth.token.json.path}")
    private String authTokenJsonPath;

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

    /**
     * Read auth_tokens.json
     */
    public Map<String, String> readAuthTokenJson() {
        try {
            Path path = Paths.get(authTokenJsonPath);
            String content = Files.readString(path);

            return jsonMapper.readValue(
                    content,
                    new TypeReference<Map<String, String>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to read auth_tokens.json", e);
        }
    }

    /**
     * Read Config.yaml
     */
    public Map<String, Object> readConfigYaml() {
        try {
            Path path = Paths.get(configYamlPath);
            String content = Files.readString(path);

            return yamlMapper.readValue(
                    content,
                    new TypeReference<Map<String, Object>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Config.yaml", e);
        }
    }
}
