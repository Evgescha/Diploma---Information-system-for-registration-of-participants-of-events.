package com.hescha.accountingforeventparticipants.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

import com.hescha.accountingforeventparticipants.entity.Event;
import com.hescha.accountingforeventparticipants.entity.User;
import com.hescha.accountingforeventparticipants.service.EventService;
import com.hescha.accountingforeventparticipants.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private EventService eventService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    String get(Model model) {
        List<User> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "user-list";
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {
        if (id != null) {
            User entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new User());
        }
        return "user-add-edit";
    }

    @PostMapping(path = "/create")
    public String createOrUpdate(User entity) {
        if (entity.getId() == null)
            service.userRegistration(entity);
        else {
            User read = service.read(entity.getId());
            read.setFio(entity.getFio());
            read.setEmail(entity.getEmail());
            read.setPhone(entity.getPhone());
            read.setDateBorn(entity.getDateBorn());
            read.setPassword(passwordEncoder.encode(entity.getPassword()));
            service.update(read);
        }
        return "redirect:/user";
    }

    @GetMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/user";
    }

}
