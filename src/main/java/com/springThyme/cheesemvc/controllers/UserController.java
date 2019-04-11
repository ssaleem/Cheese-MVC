package com.springThyme.cheesemvc.controllers;

import com.springThyme.cheesemvc.models.User;
import com.springThyme.cheesemvc.models.data.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("users", UserManager.getAll());
        return "user/index";
    }

    @RequestMapping(value = "add")
    public String add(){
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAdd(Model model, @ModelAttribute User user, String verify){
        if(user.getPassword().equals(verify)) {
            UserManager.addUser(user);
            return "redirect:";
        }
        else {
            model.addAttribute("name", user.getName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("error", "Password does not match");
            return "user/add";
        }

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
