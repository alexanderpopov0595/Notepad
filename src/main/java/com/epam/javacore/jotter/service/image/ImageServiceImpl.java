package com.epam.javacore.jotter.service.image;

import com.epam.javacore.jotter.exceptions.ImageUploadException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger logger= Logger.getLogger(ImageServiceImpl.class);

    public void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new ImageUploadException();
        }
    }

    public void saveImage( MultipartFile image, String login) {
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "jotter" + File.separator+"images"+ File.separator);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try( BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(dir + File.separator + login+".jpg"))) {
            stream.write(image.getBytes());
        } catch (IOException e) {
            logger.error("Unable to save image: "+ e.getStackTrace());
            throw new ImageUploadException();
        }
    }

    public void uploadImage(MultipartFile image, String login) {
        if (!image.isEmpty()) {
            validateImage(image);
            saveImage(image, login);
        }
    }

    @Override
    public void deleteImage(String login) {
        String rootPath = System.getProperty("catalina.home");
        File file = new File(rootPath + File.separator + "jotter"+File.separator+"images" + File.separator+login+".jpg");
        file.delete();
    }

}