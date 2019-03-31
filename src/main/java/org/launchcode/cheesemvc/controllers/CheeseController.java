package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("cheese")
public class CheeseController {

    private int cheeseId;
    private String name;
    private String description;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public static String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute(new Cheese());
        return "cheese/add";
    }

    // request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(Model model, @ModelAttribute @Valid Cheese newCheese, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/add";
        }

        CheeseData.add(newCheese);

        //redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public static String displayRemoveCheeseForm(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/remove";
    }

    //Request path: /cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
           CheeseData.remove(cheeseId);
        }
        return "redirect:";
    }

    // cheese/edit/
    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {

        Cheese cheese=CheeseData.getById(cheeseId);

        model.addAttribute(cheese);
        model.addAttribute("title", "Edit Cheese");
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/edit";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid Cheese newCheese, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/edit";
        }

        Cheese cheese;
        cheese = CheeseData.getById(newCheese.getCheeseId());
        cheese.setName(newCheese.getName());
        cheese.setDescription(newCheese.getDescription());
        cheese.setType(newCheese.getType());
        cheese.setRating(newCheese.getRating());
        return "redirect:/cheese";
    }
}


