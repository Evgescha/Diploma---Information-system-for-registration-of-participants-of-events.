package com.hescha.accountingforeventparticipants.controller;

import com.hescha.accountingforeventparticipants.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.sql.Date;

@Controller
@RequestMapping(path = {"/", "/index"})
public class IndexController {

    @Autowired
    private EventService eventService;

    @GetMapping
    String getIndex(Model model) {
        model.addAttribute("list",
                eventService.repository.findTop2ByDateAfter(
                        new Date(System.currentTimeMillis())
                )
        );
        return "index";
    }

}
