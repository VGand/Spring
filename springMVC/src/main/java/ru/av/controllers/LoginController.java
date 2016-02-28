package ru.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Полина on 24.02.2016.
 */
@Controller
public class LoginController {

    @RequestMapping("/")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="Developer") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
