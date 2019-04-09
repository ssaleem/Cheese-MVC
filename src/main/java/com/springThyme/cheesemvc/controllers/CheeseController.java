package com.springThyme.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import utils.CheeseNameValidator;

import java.util.ArrayList;
import java.util.HashMap;


@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    private static HashMap<String, String> cheeses= new HashMap<>();

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
    public String displayAddCheeseForm(Model model,
    @RequestParam(required = false) String error){

        if(error != null){
            model.addAttribute("error", true);
        }

        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

//    add handler to handle submission of form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public static String processAddCheeseForm(
            @RequestParam String cheese,
            @RequestParam String description){

        if(cheese.equals("") || description.equals("") || CheeseNameValidator.isInValid(cheese)){
//            use redirect to send query parameters, simple return will not work
            return "redirect:add?error=true";
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
            @RequestParam(required = false) String cheeseSelection){

        if(!cheeseSelection.equals("")){
            cheeses.remove(cheeseSelection);
        }

        return "redirect:";
    }

    @RequestMapping(value = "removeMultiple")
    public String displayRemoveMultipleCheeseForm(Model model){

        model.addAttribute("cheeses", cheeses);
        return "/cheese/removeMultiple";

    }

//    to protect from error caused by absence of a parameter, do 2 things
//    1. make required = false in annotation
//    2. check for null within method
    @RequestMapping(value = "removeMultiple", method = RequestMethod.POST)
    public String processRemoveMultipleCheeseForm(
            @RequestParam(required = false) ArrayList<String> cheese){

        if(cheese != null){
            for(String cheeseName: cheese){
                cheeses.remove(cheeseName);
            }
        }

        return "redirect:";
    }



}
