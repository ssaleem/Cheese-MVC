package com.springThyme.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    private static HashMap<String, String> cheeses= new HashMap<>();
//    static {
//        cheeses.add("Cheddar");
//    }

    //do not use @ResponseBody annotation with templates
    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title", "My Cheeses");
        model.addAttribute("cheeses", cheeses);
        return "cheese/index";
    }

//    add handler to show form
    @RequestMapping(value="add")
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

//    add handler to handle submission of form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public static String processAddCheeseForm(
            @RequestParam String cheese,
            @RequestParam String description){

        if(cheese.equals("") || description.equals("")){
            return "cheese/add";
        }

        cheeses.put(cheese, description);

        // Redirect to /cheese (Redirect is relative to the request mapping of controller)
        return "redirect:";
    }

    @RequestMapping(value = "remove")
    public String displayRemoveCheeseForm(Model model){
        model.addAttribute("cheeseList", cheeses.keySet());
        return "/cheese/remove";

    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(
            @RequestParam String cheeseSelection){

        cheeses.remove(cheeseSelection);
        return "redirect:";
    }

}
