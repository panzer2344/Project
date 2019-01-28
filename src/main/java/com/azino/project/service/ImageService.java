package com.azino.project.service;

import com.azino.project.model.Image;
import com.azino.project.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService extends BaseService<Image> {

    Image save(MultipartFile file, User author);

    String getEncodedImage(Long id);

}
