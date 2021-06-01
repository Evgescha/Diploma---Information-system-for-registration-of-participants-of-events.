package com.hescha.accountingforeventparticipants.controller;

import com.hescha.accountingforeventparticipants.entity.User;
import com.hescha.accountingforeventparticipants.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public String registrationPage(Model model, @ModelAttribute User user) {
        boolean success = userService.userRegistration(user);
        String response = success
                ? "Успешно зарегистрирован"
                : "Ошибка регистрации. Попробуйте позже.";
        model.addAttribute("success", response);
        return "login";
    }

    @GetMapping
    public String getRegistration() {
        return "login";
    }

}
