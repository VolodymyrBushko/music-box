package com.vbushko.musicbox.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobStorageConfig {

    @Value("${azure.blob-storage.connection-string}")
    private String connectionString;

    @Value("${azure.blob-storage.containers.audios.name}")
    private String containerName;

    @Bean
    public BlobContainerClient blobContainerClient() {
        BlobServiceClient serviceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        return serviceClient.getBlobContainerClient(containerName);
    }
}
