package com.azino.project.controller;

import com.azino.project.model.Image;
import com.azino.project.service.ImageService;
import com.azino.project.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("images")
public class ImageController {

    private final ImageService imageService;
    private final UserService userService;

    public ImageController(ImageService imageService, UserService userService){
        this.imageService = imageService;
        this.userService = userService;
    }

    /*@PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SuppressWarnings("unchecked")
    public Image upload(@RequestParam("avatar") MultipartFile file){
        Optional<User> userOptional = userService.findById((long) 1);
        User user = userOptional.orElse(new User());
        return imageService.save(file, user);
    }*/

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SuppressWarnings("unchecked")
    public Image upload(@RequestParam("avatar") MultipartFile file, Principal principal) {
        //Optional<User> userOptional = userService.findById((long) 1);
        User user = (User) ((Authentication) principal).getPrincipal();
        //User user = userOptional.orElse(new User());
        return imageService.save(file, userService.fromUserDetailsUser(user));
    }

    @GetMapping("upload")
    public ModelAndView uploadImage(){
        return new ModelAndView("images/uploadImage");
    }

    @GetMapping("getpage")
    public ModelAndView getPage() {
        return new ModelAndView("images/getImage");
    }

    /*@GetMapping("{id}")
    public String getEncodedImage(@PathVariable Long id){
        return imageService.getEncodedImage(id);
    }*/

    @GetMapping("{id}")
    public String getEncodedImage(@PathVariable Long id) {
        //return imageService.findById(id);
        return imageService.findById(id).orElse(new Image()).getData();
    }

}
