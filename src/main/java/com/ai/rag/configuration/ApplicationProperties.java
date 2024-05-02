package com.ai.rag.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationProperties {

    @Value("${web.config.allowed-origin-urls}")
    private String[] allowedOriginURLs;

    @Value("${web.config.max-age}")
    private Long maxAge;

    @Value("${spring.ai.vectorstore.qdrant.host}")
    private String vectorStoreHostname;

    @Value("${spring.ai.vectorstore.qdrant.port}")
    private int vectorStorePort;

    @Value("${spring.ai.vectorstore.qdrant.collection-name}")
    private String vectorStoreCollectionName;

    @Value("${spring.ai.vectorstore.qdrant.api-key}")
    private String vectorStoreApiKey;

    @Value("${spring.ai.openai.chat.options.model}")
    private String openAiModel;

    @Value("${spring.ai.openai.chat.options.temperature}")
    private Float modelTemperature;

    @Value("${spring.ai.openai.chat.options.max-tokens}")
    private int modelMaxTokens;

    @Value("${spring.ai.openai.chat.options.top-p}")
    private String modelTopP;

    @Value("${spring.ai.openai.chat.options.frequency-penalty}")
    private String modelFrequencyPenalty;

    @Value("${spring.ai.openai.chat.options.api-key}")
    private String openAiApiKey;

    public Long getMaxAge() {
        return maxAge;
    }

    public String[] getAllowedOriginURLs() {
        return allowedOriginURLs;
    }

    public String getVectorStoreHostname() {
        return vectorStoreHostname;
    }

    public int getVectorStorePort() {
        return vectorStorePort;
    }

    public String getVectorStoreCollectionName() {
        return vectorStoreCollectionName;
    }

    public String getVectorStoreApiKey() {
        return vectorStoreApiKey;
    }

    public String getOpenAiApiKey() {
        return openAiApiKey;
    }

    public String getOpenAiModel() {
        return openAiModel;
    }

    public Float getModelTemperature() {
        return modelTemperature;
    }

    public int getModelMaxTokens() {
        return modelMaxTokens;
    }

    public String getModelTopP() {
        return modelTopP;
    }

    public String getModelFrequencyPenalty() {
        return modelFrequencyPenalty;
    }
}
