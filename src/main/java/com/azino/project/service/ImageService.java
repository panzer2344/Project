package com.azino.project.service;

import com.azino.project.model.Image;
import com.azino.project.model.User;
import com.azino.project.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class ImageService extends BaseService<Image, ImageRepository> {

    public ImageService(ImageRepository imageRepository){
        super(imageRepository);
    }

    public Image save(MultipartFile file, User author){
        Image image = null;
        try {
            image = new Image(file.getBytes(), author);
            save(image);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        return image;
    }

    public String getEncodedImage(Long id){
        String result = "";
        Optional<Image> image = findById(id);
        if(image.isPresent()){
            result = Base64.getEncoder().encodeToString(image.get().getData());
        }
        return result;
    }

}
