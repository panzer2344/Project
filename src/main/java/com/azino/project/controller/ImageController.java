package com.azino.project.controller;

import com.azino.project.model.Image;
import com.azino.project.model.User;
import com.azino.project.service.ImageService;
import com.azino.project.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("images")
public class ImageController {

    private final ImageService imageService;
    private final UserService userService;

    public ImageController(ImageService imageService, UserService userService){
        this.imageService = imageService;
        this.userService = userService;
    }

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SuppressWarnings("unchecked")
    public Image upload(@RequestParam("avatar") MultipartFile file){
        Optional<User> userOptional = userService.findById((long) 1);
        User user = userOptional.orElse(new User());
        return imageService.save(file, user);
    }

    @GetMapping("upload")
    public ModelAndView uploadImage(){
        return new ModelAndView("images/uploadImage");
    }

    @GetMapping("getpage")
    public ModelAndView getPage() {
        return new ModelAndView("images/getImage");
    }

    @GetMapping("{id}")
    public String getEncodedImage(@PathVariable Long id){
        return imageService.getEncodedImage(id);
    }

}
