package com.springThyme.cheesemvc.controllers;

import com.springThyme.cheesemvc.models.Cheese;
import com.springThyme.cheesemvc.models.CheeseType;
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
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(
            @ModelAttribute @Valid Cheese newCheese,
            Errors errors,
            Model model){

        if(errors.hasErrors()){
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/add";
        }
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
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(
            @ModelAttribute @Valid Cheese theCheese,
            Errors errors,
            Model model){

        if(errors.hasErrors()){
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/edit";
        }

        cheeseDAO.save(theCheese);

        return "redirect:";
    }

    @RequestMapping(value = "search")
    public String displaySearchForm(Model model){
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/search";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String processSearchForm(@RequestParam CheeseType cheeseType, Model model) {

        model.addAttribute("cheeseTypes", CheeseType.values());
        if(cheeseType == null){
            return "cheese/search";
        }
        model.addAttribute("cheeses", cheeseDAO.findByCheeseType(cheeseType));
        return "cheese/search";
    }

}
