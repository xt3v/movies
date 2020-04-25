package com.soyeyo.movies.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
@Getter
@Setter
public class FileUploaderImpl implements FileUploader {

    private  Path fileStorageLocation;

    @Value("${uploadDir.path}")
    private String uploadDir;

    public FileUploaderImpl(){

    }

    private void createPath(){
        this.fileStorageLocation = Paths.get(uploadDir)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            System.out.println("not created");
        }
    }


    public String storeFile(MultipartFile file) {
        createPath();
        // Normalize file name
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String fileName = System.nanoTime()+"."+ext;

        //String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            System.out.println("not uploaded");;
        }
        return "";
    }
}
