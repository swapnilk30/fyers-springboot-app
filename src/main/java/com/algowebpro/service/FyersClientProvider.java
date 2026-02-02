package com.algowebpro.service;

import com.algowebpro.util.ConfigFileReaderUtil;
import com.tts.in.model.FyersClass;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FyersClientProvider {

    private final ConfigFileReaderUtil configFileReaderUtil;

    public FyersClientProvider(ConfigFileReaderUtil configFileReaderUtil) {
        this.configFileReaderUtil = configFileReaderUtil;
    }

    public FyersClass getClient() {

        // Read YAML config
        Map<String, Object> config = configFileReaderUtil.readConfigYaml();
        Map<String, Object> fyersConfig =
                (Map<String, Object>) config.get("fyers");

        String clientId = (String) fyersConfig.get("client_id");

        // Read token JSON
        Map<String, String> tokenMap =
                configFileReaderUtil.readAuthTokenJson();

        String accessToken = tokenMap.get("access_token");

        // Create Fyers client
        FyersClass fyersClass = FyersClass.getInstance();
        fyersClass.clientId = clientId;
        fyersClass.accessToken = accessToken;

        return fyersClass;
    }
}
