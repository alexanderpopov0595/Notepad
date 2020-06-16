package com.epam.javacore.jotter.service.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void uploadImage(MultipartFile image, String login);

    void deleteImage(String login);

}