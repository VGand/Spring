package com.epam.spring.cinema.controllers;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Andrey_Vaganov on 4/12/2017.
 */
@EnableWebMvc
@ControllerAdvice
public class ExceptionController {
    Logger logger = Logger.getLogger(ExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    public String handleAllErrors(Model model, Exception e)   {
        logger.error(e.getMessage(), e);
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}
