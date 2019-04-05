package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "add", method= RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute("title", "User Sign-up");
        model.addAttribute(new User());
        return "user/add";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "User Sign-up");
            return "user/add";
        }
        else {
            UserData.add(user);
            model.addAttribute("user", user);
            model.addAttribute("title", "User Sign-up");
            model.addAttribute("users", UserData.getAll());
        }
        return "user/index";

    }

    @RequestMapping(value = "{userId}", method= RequestMethod.GET)
    public String displayUserInfo(Model model, @PathVariable int userId) {

        User user=UserData.getById(userId);
        model.addAttribute("user", user);
        model.addAttribute("title", "User Info");
        return "user/info";
    }

}
