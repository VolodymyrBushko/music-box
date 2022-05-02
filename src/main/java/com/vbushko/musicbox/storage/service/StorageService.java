package com.vbushko.musicbox.storage.service;

import java.io.InputStream;

public interface StorageService {

    void uploadBlob(final String name, final InputStream data);
}
