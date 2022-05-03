package com.vbushko.musicbox.storage.service;

import java.io.IOException;
import java.io.InputStream;

public interface BlobStorageService {

    void uploadBlob(final String blobName, final InputStream blobData) throws IOException;

    void removeBlob(final String blobName);
}
