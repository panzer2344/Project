package com.azino.project.controller;

import com.azino.project.model.User;
import com.azino.project.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestBody User user){
        userService.save(user);
        return "User (" + user.getName() + ") successfully added";
    }

    @GetMapping
    public Iterable getAll(){
        return userService.findAll();
    }

    @GetMapping("{id}")
    public User get(@PathVariable Long id){
        return userService.findById(id).get();
    }

    @DeleteMapping
    public String deleteAll(){
        userService.deleteAll();
        return "All users successfully deleted";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id){
        Long userId = userService.findById(id).get().getId();
        userService.deleteById(userId);
        return "User (id = " + userId + " has been has successfully deleted";
    }

}
