package com.springThyme.cheesemvc.controllers;

import com.springThyme.cheesemvc.models.Cheese;
import com.springThyme.cheesemvc.models.data.CheeseManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title", "My Cheeses");
        model.addAttribute("cheeses", CheeseManager.getAll());
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
            @ModelAttribute @Valid Cheese newCheese,
            Errors errors){

//        if(cheese.equals("") || description.equals("") || CheeseNameValidator.isInValid(cheese)){
//            // use redirect to send query parameters, simple return will not work
//            return "redirect:add?error=true";
//        }

        CheeseManager.add(newCheese);

        // Redirect to /cheese (Redirect is relative to the request mapping of controller)
        return "redirect:";
    }

    @RequestMapping(value = "remove")
    public String displayRemoveCheeseForm(Model model){

        model.addAttribute("cheeseList", CheeseManager.getAll());
        return "/cheese/remove";

    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(
            @RequestParam(required = false) int cheeseSelection){

        CheeseManager.removeCheese(cheeseSelection);
        return "redirect:";
    }

    @RequestMapping(value = "removeMultiple")
    public String displayRemoveMultipleCheeseForm(Model model){

        model.addAttribute("cheeses", CheeseManager.getAll());
        return "/cheese/removeMultiple";

    }


    @RequestMapping(value = "removeMultiple", method = RequestMethod.POST)
    public String processRemoveMultipleCheeseForm(
            @RequestParam(required = false) int[] cheeseSelection){

        if(cheeseSelection != null){
            CheeseManager.removeCheeses(cheeseSelection);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}")
    public String displayEditForm(Model model, @PathVariable int cheeseId){
        System.out.println("here");
        model.addAttribute("cheese", CheeseManager.getById(cheeseId));
        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(int ID, String name, String description){
        CheeseManager.update(ID, name, description);
        return "redirect:";
    }

}
