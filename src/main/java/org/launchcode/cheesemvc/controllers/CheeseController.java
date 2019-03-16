package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public static String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    // request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public static String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDesc) {

        Cheese cheese = new Cheese(cheeseName, cheeseDesc);
        cheeses.add(cheese);

        //redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public static String displayRemoveCheeseForm(Model model) {

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");

        return "cheese/remove";
    }

    //Request path: /cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public static String processRemoveCheeseForm(@RequestParam String removeCheese) {

        if (removeCheese.length() < 1) {
            //System.out.println("empty");
            return "redirect:";
        }

        for (Cheese cheese : cheeses) {
            if (cheese.getName().equals(removeCheese)) {
                cheeses.remove(cheese);
                return "redirect:";
            }

        }

        if (cheeses.size() < 1) {
            return "redirect:";
        }
        return "redirect:";
    }
}


