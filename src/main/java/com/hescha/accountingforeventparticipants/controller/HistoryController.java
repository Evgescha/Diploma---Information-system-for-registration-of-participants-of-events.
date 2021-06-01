package com.hescha.accountingforeventparticipants.controller;

import com.hescha.accountingforeventparticipants.entity.User;
import com.hescha.accountingforeventparticipants.service.EventService;
import com.hescha.accountingforeventparticipants.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.sql.Date;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    String getIndex(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        model.addAttribute("list", user.getEvents());
        model.addAttribute("entity", user);
        return "history";
    }

    @GetMapping("/get/{id}")
    String getIndex(Model model, @PathVariable("id") Long id) {
        User user;
        try {
            user = userService.read(id);
            model.addAttribute("list", user.getEvents());
            model.addAttribute("entity", user);
        } catch (Exception e) {
            user = new User();
            user.setFio("Пользователя с данным ид не найдено");
            model.addAttribute("entity", user);
        }
        model.addAttribute("list", user.getEvents());
        return "history";
    }
}
