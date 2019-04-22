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
    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title", "My Cheeses");
        model.addAttribute("cheeses", cheeseDAO.findAll());
        return "cheese/index";
    }

    @RequestMapping(value="add")
    public String displayAddCheeseForm(Model model){

//        following line add an attribute with the key of `cheese' to the model
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDAO.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(
            @ModelAttribute @Valid Cheese newCheese,
            Errors errors,
            @RequestParam int categoryId,
            Model model){
        Category category = categoryDAO.findOne(categoryId);

        if(errors.hasErrors() || category == null){
            model.addAttribute("categories", categoryDAO.findAll());
            return "cheese/add";
        }

        newCheese.setCategory(category);
        cheeseDAO.save(newCheese);

        // Redirect to /cheese (Redirect is relative to the request mapping of controller)
        return "redirect:";
    }

    @RequestMapping(value = "remove")
    public String displayRemoveCheeseForm(Model model){

        model.addAttribute("cheeseList", cheeseDAO.findAll());
        return "/cheese/remove";

    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(
            @RequestParam(required = false) int cheeseSelection){

        cheeseDAO.delete(cheeseSelection);
        return "redirect:";
    }

    @RequestMapping(value = "removeMultiple")
    public String displayRemoveMultipleCheeseForm(Model model){

        model.addAttribute("cheeses", cheeseDAO.findAll());
        return "/cheese/removeMultiple";

    }


    @RequestMapping(value = "removeMultiple", method = RequestMethod.POST)
    public String processRemoveMultipleCheeseForm(
            @RequestParam(required = false) int[] cheeseSelection){

        if(cheeseSelection != null){
            cheeseDAO.deleteByIdIn(cheeseSelection);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}")
    public String displayEditForm(Model model, @PathVariable int cheeseId){

        model.addAttribute("cheese", cheeseDAO.findOne(cheeseId));
        model.addAttribute("categories", categoryDAO.findAll());
        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
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

    @RequestMapping(value = "category/{categoryId}")
    public String category(Model model,
                           @PathVariable int categoryId) {

        Category category = categoryDAO.findOne(categoryId);

        if(category != null) {
            model.addAttribute("cheeses", categoryDAO.findOne(categoryId).getCheeses());
        }

        return "cheese/index";
    }

//    @RequestMapping(value = "search")
//    public String displaySearchForm(Model model){
//        model.addAttribute("categories", categoryDAO.findAll());
//        return "cheese/search";
//    }
//
//    @RequestMapping(value = "search", method = RequestMethod.POST)
//    public String processSearchForm(@RequestParam Category category, Model model) {
//
//        model.addAttribute("categories", categoryDAO.findAll());
//        if(category == null){
//            return "cheese/search";
//        }
//        model.addAttribute("cheeses", cheeseDAO.findByCheeseType(cheeseType));
//        return "cheese/search";
//    }

}
