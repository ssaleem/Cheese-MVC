package com.springThyme.cheesemvc.controllers;

import com.springThyme.cheesemvc.models.Cheese;
import com.springThyme.cheesemvc.models.Menu;
import com.springThyme.cheesemvc.models.data.CheeseDAO;
import com.springThyme.cheesemvc.models.data.MenuDAO;
import com.springThyme.cheesemvc.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private CheeseDAO cheeseDAO;

    @Autowired
    private MenuDAO menuDAO;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("title", "Menus");
        model.addAttribute("menus", menuDAO.findAll());
        return "menu/index";
    }

    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @PostMapping("add")
    public String processAdd(Model model,
                            @ModelAttribute @Valid Menu menu,
                             Errors errors){
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        menuDAO.save(menu);
        return "redirect:view/" + menu.getId();
    }

    @GetMapping("view/{menuId}")
    public String viewMenu(Model model,
                           @PathVariable int menuId){
        Menu menu = menuDAO.findOne(menuId);
        if(menu != null){
            model.addAttribute("menu", menu);
        }
        return "menu/view";
    }

    @GetMapping("add-item/{menuId}")
    public String addItem(@PathVariable int menuId,
                          Model model){
        Menu menu = menuDAO.findOne(menuId);
        Iterable<Cheese> cheeses = cheeseDAO.findAll();
        AddMenuItemForm addMenuItemForm = new AddMenuItemForm(menu,cheeses);

        model.addAttribute("form", addMenuItemForm);
        model.addAttribute("title", "Add item to menu: " + menu.getName());
        return "menu/add-item";
    }

    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.POST)
    public String processAddItem(@ModelAttribute @Valid AddMenuItemForm addMenuItemForm,
                          Errors errors,
                          @RequestParam int menuId){
        if(errors.hasErrors()){
            return "menu/add-item";
        }
        Cheese cheese = cheeseDAO.findOne(addMenuItemForm.getCheeseId());
        Menu menu = menuDAO.findOne(menuId);
        menu.addItem(cheese);
        menuDAO.save(menu);

        return "redirect:/menu/view/" + menuId;
    }

}
