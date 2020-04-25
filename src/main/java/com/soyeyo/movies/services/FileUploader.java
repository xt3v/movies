package com.soyeyo.movies.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader{
    String storeFile(MultipartFile file);
}
