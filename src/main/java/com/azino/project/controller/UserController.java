package com.azino.project.controller;

import com.azino.project.model.DTO.form.FormUser;
import com.azino.project.model.User;
import com.azino.project.service.UserService;
import com.azino.project.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

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

    /*@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestBody User user) {
        userService.save(user);
        return "User (" + user.getName() + ") successfully added";
    }*/

    /*@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Transactional
    public RedirectView add(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
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
    }*/

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public String add(@Valid @RequestBody FormUser formUser, BindingResult bindingResult, ModelMap modelMap, HttpServletResponse response){
        try {
            if(bindingResult.hasErrors()){
                StringBuilder error = WebUtils.bindingResultErrorsToString(bindingResult);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, error.toString());
                return "Error!";
            }
            if(null != formUser){
                if(userService.findByName(formUser.getName()) != null){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User with this username already exist");
                    return "Error!";
                }
                if(null == formUser.getPassword() || formUser.getPassword().isEmpty()){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Password mustn't be empty");
                    return "Error!";
                }
                User user = userService.fromFormUser(formUser);
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                userService.save(user);
                response.setStatus(HttpServletResponse.SC_CREATED);
                /*response.sendRedirect("/menu/users/login");*/
                return "User successfully saved";
            }
        }catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
        }
        return "Error!";
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

    @DeleteMapping("{name}")
    public ModelAndView delete(@PathVariable String name) {
        String username = userService.findByName(name).getName();
        userService.deleteByName(username);
        return new ModelAndView("redirect:/menu/index", HttpStatus.OK);
    }

    @GetMapping("update/{name}")
    public ModelAndView getUpdatePage(ModelMap modelMap, @PathVariable String name){
        modelMap.addAttribute("user", userService.findByName(name));
        return new ModelAndView("forward:/menu/users/update");
    }

    @PutMapping("update/{name}")
    @Transactional
    public String update(@PathVariable String name, @Valid @RequestBody User formUser, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            try{
                StringBuilder error = WebUtils.bindingResultErrorsToString(bindingResult);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, error.toString());
                return "Error!";
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
            finally {
            }
        }
        User user = userService.findByName(name);
        if(formUser.getFirstName() != null){
            user.setFirstName(formUser.getFirstName());
        }
        if(formUser.getLastName() != null){
            user.setLastName(formUser.getLastName());
        }
        if(formUser.getAge() != null){
            user.setAge(formUser.getAge());
        }
        return "Ok";
    }

}
