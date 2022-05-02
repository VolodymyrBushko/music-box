package com.vbushko.musicbox.storage.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.vbushko.musicbox.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AzureBlobStorageService implements StorageService {

    private final BlobContainerClient containerClient;

    @Override
    public boolean uploadBlob(final String name, final InputStream data) {
        try {
            BlobClient blobClient = containerClient.getBlobClient(name);
            blobClient.upload(data, data.available());
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }
}
