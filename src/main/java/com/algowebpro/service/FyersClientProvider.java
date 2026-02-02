package com.algowebpro.service;

import com.algowebpro.util.ConfigFileReaderUtil;
import com.tts.in.model.FyersClass;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FyersClientProvider {

    private final ConfigFileReaderUtil configFileReaderUtil;

    private FyersClass cachedClient;

    public FyersClientProvider(ConfigFileReaderUtil configFileReaderUtil) {
        this.configFileReaderUtil = configFileReaderUtil;
    }

    public synchronized FyersClass getClient() {

        // return cached instance if already created
        if (cachedClient != null) {
            return cachedClient;
        }

        // Read YAML config
        Map<String, Object> config = configFileReaderUtil.readConfigYaml();
        Map<String, Object> fyersConfig =
                (Map<String, Object>) config.get("fyers");

        String clientId = (String) fyersConfig.get("client_id");

        // Read token JSON
        Map<String, String> tokenMap =
                configFileReaderUtil.readAuthTokenJson();

        String accessToken = tokenMap.get("access_token");

        // Create client ONCE
        FyersClass fyersClass = FyersClass.getInstance();
        fyersClass.clientId = clientId;
        fyersClass.accessToken = accessToken;

        cachedClient = fyersClass;

        return cachedClient;
    }


    /**
     * Call this when token is refreshed
     */
    public synchronized void refreshAccessToken(String newToken) {
        if (cachedClient != null) {
            cachedClient.accessToken = newToken;
        }
    }
}
