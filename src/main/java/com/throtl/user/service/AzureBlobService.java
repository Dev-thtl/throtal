package com.throtl.user.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.OffsetDateTime;

@Service
public class AzureBlobService {


    private BlobServiceClient blobServiceClient;

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    // Initialize BlobServiceClient after properties are injected
    @PostConstruct
    public void init() {
        try {
            this.blobServiceClient = new BlobServiceClientBuilder()
                    .connectionString(connectionString)
                    .buildClient();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create BlobServiceClient: " + e.getMessage(), e);
        }
    }

    public String uploadFile(InputStream fileStream, String fileName) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        try {
            blobClient.upload(fileStream, fileStream.available(), true);
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file: " + e.getMessage(), e);
        }
        return blobClient.getBlobUrl();
    }

    public String generateDownloadLink(String fileName) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        String sasUrl;
        try {
            BlobServiceSasSignatureValues values = new BlobServiceSasSignatureValues(
                    OffsetDateTime.now().plusHours(24),
                    BlobSasPermission.parse("r"));

            sasUrl = blobClient.generateSas(values);

        } catch (Exception e) {
            throw new RuntimeException("Error generating download link: " + e.getMessage(), e);
        }
        return blobClient.getBlobUrl() + "?" + sasUrl;
    }
}
