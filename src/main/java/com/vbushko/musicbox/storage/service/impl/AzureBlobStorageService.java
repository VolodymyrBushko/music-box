package com.vbushko.musicbox.storage.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.vbushko.musicbox.storage.service.BlobStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AzureBlobStorageService implements BlobStorageService {

    private final BlobContainerClient containerClient;

    @Override
    public void uploadBlob(final String blobName, final InputStream blobData) throws IOException {
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        blobClient.upload(blobData, blobData.available());
    }

    @Override
    public void removeBlob(final String blobName) {
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        blobClient.delete();
    }
}
