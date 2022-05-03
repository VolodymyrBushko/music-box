package com.vbushko.musicbox.storage.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.vbushko.musicbox.storage.service.BlobStorageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AzureBlobStorageService implements BlobStorageService {

    private final BlobContainerClient containerClient;

    @Override
    @SneakyThrows
    public void uploadBlob(final String name, final InputStream data) {
        BlobClient blobClient = containerClient.getBlobClient(name);
        blobClient.upload(data, data.available());
    }

    @Override
    public void removeBlob(final String name) {
        BlobClient blobClient = containerClient.getBlobClient(name);
        blobClient.delete();
    }
}
