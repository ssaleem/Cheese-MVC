package com.springThyme.cheesemvc.controllers;

import com.springThyme.cheesemvc.models.User;
import com.springThyme.cheesemvc.models.data.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDAO.findAll());
        return "user/index";
    }

    @GetMapping(value = "add")
    public String add(Model model){
        model.addAttribute(new User());
        model.addAttribute("title", "Add User");
        return "user/add";
    }

    @PostMapping(value = "add")
    public String processAdd(
            Model model,
            @ModelAttribute @Valid User user,
            Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("title", "Add User");
            return "user/add";
        }

        userDAO.save(user);
        return "redirect:";
    }

    @GetMapping(value="{id}")
    public String getUser(
            Model model,
            @PathVariable long id){
        User user = userDAO.findOne(id);
        model.addAttribute("title", "User Detail");
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("joinDate", user.getJoinDate());

        return "user/user";
    }
}
