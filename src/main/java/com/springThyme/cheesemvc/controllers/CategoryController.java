package com.springThyme.cheesemvc.controllers;

import com.springThyme.cheesemvc.models.Category;
import com.springThyme.cheesemvc.models.data.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDAO categoryDao;

    @GetMapping("")
    public String index(Model model){

        model.addAttribute("title", "Cheese Categories");
        model.addAttribute("categories", categoryDao.findAll());
        return "category/index";
    }

    @GetMapping("add")
    public String add(Model model) {

        model.addAttribute("title", "Add Cheese Category");
        model.addAttribute(new Category());
        return "category/add";
    }

    @PostMapping("add")
    public String add(Model model,
                      @ModelAttribute @Valid Category category,
                      Errors errors) {
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Cheese Category");
            return "category/add";
        }

        categoryDao.save(category);
        return "redirect:";
    }
}
