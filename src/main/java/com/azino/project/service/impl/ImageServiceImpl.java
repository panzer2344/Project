package com.azino.project.service.impl;

import com.azino.project.model.Image;
import com.azino.project.model.User;
import com.azino.project.repository.ImageRepository;
import com.azino.project.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ImageServiceImpl extends BaseServiceImpl<Image, ImageRepository> implements ImageService {

    public ImageServiceImpl(ImageRepository imageRepository){
        super(imageRepository);
    }

    public Image save(MultipartFile file, User author) {
        Image image = null;
        try {
            image = new Image(Base64.getEncoder().encodeToString(file.getBytes()), author);
            save(image);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return image;
    }

    public String getEncodedImage(Long id) {
        return findById(id).orElse(new Image()).getData();
    }
}
