package com.springThyme.cheesemvc.controllers;

import com.springThyme.cheesemvc.models.User;
import com.springThyme.cheesemvc.models.data.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("users", UserManager.getAll());
        return "user/index";
    }

    @RequestMapping(value = "add")
    public String add(Model model){
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAdd(
            Model model,
            @ModelAttribute @Valid User user,
            Errors errors,
            String verify){

        boolean verifyError = false;
        if(verify == null ||  !user.getPassword().equals(verify)) {
            user.setPassword("");
            model.addAttribute("error", "Password does not match");
            verifyError = true;
        }

        if(errors.hasErrors() || verifyError){
//            model.addAttribute(user);
            return "user/add";
        }

        UserManager.addUser(user);
        return "redirect:";
    }

    @RequestMapping(value="{id}")
    public String getUser(
            Model model,
            @PathVariable int id){
        User user = UserManager.getByID(id);
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("joinDate", user.getJoinDate());

        return "user/user";
    }
}
