package com.azino.project.service;

import com.azino.project.model.Image;
import com.azino.project.model.User;
import com.azino.project.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService extends BaseService<Image, ImageRepository> {

    public ImageService(ImageRepository imageRepository){
        super(imageRepository);
    }

    public Image save(MultipartFile file, User author){
        Image image = null;
        try {
            byte[] bytes = file.getBytes();
            Byte[] data = new Byte[bytes.length];
            for(int i = 0; i < bytes.length; i++){
                data[i] = bytes[i];
            }
            image = new Image(data, author);
            save(image);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        return image;
    }

}
