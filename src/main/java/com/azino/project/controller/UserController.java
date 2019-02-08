package com.azino.project.controller;

import com.azino.project.model.User;
import com.azino.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController()
@RequestMapping("users")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestBody User user) {
        userService.save(user);
        return "User (" + user.getName() + ") successfully added";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Transactional
    public RedirectView add(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        if (user != null) {
            if (user.getName() != null && !user.getName().equals("") &&
                    user.getPassword() != null && !user.getPassword().equals("")) {

                if (userService.findByName(user.getName()) == null) {
                    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                    userService.save(user);
                    return new RedirectView("login");
                } else {
                    redirectAttributes.addAttribute("error", "true");
                    redirectAttributes.addAttribute("errorName", "User with this username already exist");
                    return new RedirectView("signUp");
                }
            }else{
                redirectAttributes.addAttribute("error", "true");
                redirectAttributes.addAttribute("errorName", "Username and password mustn't be empty");
                return new RedirectView("signUp");
            }
        } else {
            redirectAttributes.addAttribute("error", "true");
            redirectAttributes.addAttribute("errorName", "Cannot init user");
            return new RedirectView("signUp");
        }
    }

    @GetMapping
    public Iterable getAll() {
        return userService.findAll();
    }

    /*@GetMapping("{id}")
    public User get(@PathVariable Long id) {
        return userService.findById(id).get();
    }*/

    @GetMapping("{name}")
    public User get(@PathVariable String name) {
        return userService.findByName(name);
    }

    @DeleteMapping
    public String deleteAll() {
        userService.deleteAll();
        return "All users successfully deleted";
    }

    /*@DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        Long userId = userService.findById(id).get().getId();
        userService.deleteById(userId);
        return "User (id = " + userId + " has been has successfully deleted";
    }*/

    @DeleteMapping("{name}")
    public ModelAndView delete(@PathVariable String name) {
        String username = userService.findByName(name).getName();
        userService.deleteByName(username);
        return new ModelAndView("redirect:/menu/index", HttpStatus.OK);
    }

}
