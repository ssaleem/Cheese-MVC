package com.springThyme.cheesemvc.controllers;

import com.springThyme.cheesemvc.models.Category;
import com.springThyme.cheesemvc.models.Cheese;
import com.springThyme.cheesemvc.models.data.CategoryDAO;
import com.springThyme.cheesemvc.models.data.CheeseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @Autowired //instance is created by framework for us
    private CheeseDAO cheeseDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    // Request path: /cheese
    @GetMapping(value = "")
    public String index(Model model){
        model.addAttribute("title", "My Cheeses");
        model.addAttribute("cheeses", cheeseDAO.findAll());
        return "cheese/index";
    }

    @GetMapping(value="add")
    public String displayAddCheeseForm(Model model){

//        following line add an attribute with the key of `cheese' to the model
        model.addAttribute(new Cheese());
        model.addAttribute("title", "Add Cheese");
        model.addAttribute("categories", categoryDAO.findAll());
        return "cheese/add";
    }

    @PostMapping(value = "add")
    public String processAddCheeseForm(
            @ModelAttribute @Valid Cheese newCheese,
            Errors errors,
            @RequestParam int categoryId,
            Model model){
        Category category = categoryDAO.findOne(categoryId);

        if(errors.hasErrors() || category == null){
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDAO.findAll());
            return "cheese/add";
        }

        newCheese.setCategory(category);
        cheeseDAO.save(newCheese);

        // Redirect to /cheese (Redirect is relative to the request mapping of controller)
        return "redirect:";
    }

    @GetMapping(value = "remove")
    public String displayRemoveCheeseForm(Model model){

        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeseList", cheeseDAO.findAll());
        return "/cheese/remove";

    }

    @PostMapping(value = "remove")
    public String processRemoveCheeseForm(
            @RequestParam(required = false) int cheeseSelection){
        cheeseDAO.delete(cheeseSelection);
        return "redirect:";
    }

    @GetMapping(value = "removeMultiple")
    public String displayRemoveMultipleCheeseForm(Model model){

        model.addAttribute("title", "Remove Cheese/s");
        model.addAttribute("cheeses", cheeseDAO.findAll());
        return "/cheese/removeMultiple";

    }


    @PostMapping(value = "removeMultiple")
    public String processRemoveMultipleCheeseForm(
            @RequestParam(required = false) int[] cheeseSelection){

        if(cheeseSelection != null){
            cheeseDAO.deleteByIdIn(cheeseSelection);
        }

        return "redirect:";
    }

    @GetMapping(value = "edit/{cheeseId}")
    public String displayEditForm(Model model, @PathVariable int cheeseId){

        model.addAttribute("cheese", cheeseDAO.findOne(cheeseId));
        model.addAttribute("categories", categoryDAO.findAll());
        return "cheese/edit";
    }

    @PostMapping(value = "edit")
    public String processEditForm(
            @ModelAttribute @Valid Cheese theCheese,
            Errors errors,
            @RequestParam int categoryId,
            Model model){

        Category category = categoryDAO.findOne(categoryId);

        if(errors.hasErrors() || category == null){
            model.addAttribute("categories", categoryDAO.findAll());
            return "cheese/edit";
        }

        theCheese.setCategory(category);
        cheeseDAO.save(theCheese);

        return "redirect:";
    }

    @GetMapping(value = "category/{categoryId}")
    public String displayCheesesByCategory(Model model,
                           @PathVariable int categoryId) {

        Category category = categoryDAO.findOne(categoryId);

        if(category != null) {
            model.addAttribute("title", "Cheese with category: " + categoryDAO.findOne(categoryId).getName());
            model.addAttribute("cheeses", categoryDAO.findOne(categoryId).getCheeses());
        }

        return "cheese/index";
    }

}
