package com.vbushko.musicbox.storage.service;

import java.io.InputStream;

public interface BlobStorageService {

    void uploadBlob(final String name, final InputStream data);

    void removeBlob(final String name);
}
