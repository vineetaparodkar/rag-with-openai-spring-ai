package com.ai.rag.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.qdrant.QdrantVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorDBConfiguration {

    private final ApplicationProperties applicationProperties;

    private String hostname;

    private int port;

    private String collectionName;

    private String apiKey;

    public VectorDBConfiguration(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @PostConstruct
    private void init() {
        hostname = applicationProperties.getVectorStoreHostname();
        port = applicationProperties.getVectorStorePort();
        collectionName = applicationProperties.getVectorStoreCollectionName();
        apiKey = applicationProperties.getVectorStoreApiKey();

    }

    @Bean
    public QdrantVectorStore.QdrantVectorStoreConfig qdrantVectorStoreConfig() {

        return QdrantVectorStore.QdrantVectorStoreConfig.builder()
                .withHost(hostname)
                .withPort(port)
                .withCollectionName(collectionName)
                .withApiKey(apiKey)
                .build();
    }

    @Bean
    public VectorStore vectorStore(QdrantVectorStore.QdrantVectorStoreConfig config, EmbeddingClient embeddingClient) {
        return new QdrantVectorStore(config, embeddingClient);
    }
}
