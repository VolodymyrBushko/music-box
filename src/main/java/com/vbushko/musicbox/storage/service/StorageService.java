package com.vbushko.musicbox.storage.service;

import java.io.InputStream;

public interface StorageService {

    boolean uploadBlob(final String name, final InputStream data);
}
