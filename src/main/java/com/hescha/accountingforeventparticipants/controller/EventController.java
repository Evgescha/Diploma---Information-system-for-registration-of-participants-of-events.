package com.hescha.accountingforeventparticipants.controller;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.util.Collections;
import java.util.List;

import com.hescha.accountingforeventparticipants.entity.Category;
import com.hescha.accountingforeventparticipants.entity.Event;
import com.hescha.accountingforeventparticipants.entity.User;
import com.hescha.accountingforeventparticipants.service.CategoryService;
import com.hescha.accountingforeventparticipants.service.EventService;
import com.hescha.accountingforeventparticipants.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService service;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CategoryService categoryEventService;

    @GetMapping
    public String get(Model model) {
        List<Event> list = service.repository.findAll();
        Collections.reverse(list);
        model.addAttribute("list", list);
        return "event-list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws Exception {
        Event read = service.read(id);
        read.getUsers().clear();
        service.update(read);
        service.delete(id);
        return "redirect:/event";
    }

    @GetMapping("/id/{id}")
    public String description(Model model, @PathVariable("id") Long id,
                              Principal principal) {
        Event event = service.read(id);

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            if (user != null) {
                model.addAttribute("isHaveEvent", user.isHaveEvent(event));
            }
        }
        model.addAttribute("entity", event);
        model.addAttribute("currentDate", new Date(System.currentTimeMillis()));
        model.addAttribute("list",
                service.repository.findTop2ByDateAfter(new Date(System.currentTimeMillis())));
        return "event-detail";
    }

    @GetMapping("/addEvent/{id}")
    public String addEVentToList(@PathVariable("id") Long id,
                                 Principal principal) {
        try {
            Event event = service.read(id);
            User user = userService.findByUsername(principal.getName());

            event.getUsers().add(user);
            user.getEvents().add(event);

            service.update(event);
            userService.update(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/event/id/" + id;
    }


    @GetMapping("/removeEvent/{id}")
    public String removeEVentToList(@PathVariable("id") Long id,
                                    Principal principal) {
        Event event = service.read(id);
        try {
            User user = userService.findByUsername(principal.getName());
            event.getUsers().remove(user);
            user.getEvents().remove(event);

            service.update(event);
            userService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/event/id/" + id;
    }


    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {
        List<Category> categories = categoryEventService.repository.findAll();
        if (id != null) {
            Event entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Event());
        }
        model.addAttribute("categories", categories);
        return "event-add-edit";
    }

    @PostMapping("/create")
    public String createOrUpdate(Event entity, @Param("catId") Long catId,
                                 @Param("times") String times) {

        if (entity.getId() == null) {
            entity.setCategory(categoryEventService.read(catId));
            setTime(entity, times);
            service.create(entity);
        } else {
            Event event = service.read(entity.getId());

            setTime(event, times);
            event.setCategory(categoryEventService.read(catId));
            event.setDate(entity.getDate());
            event.setDescription(entity.getDescription());
            event.setImage(entity.getImage());
            event.setName(entity.getName());
            service.update(event);
        }
        return "redirect:/event";
    }

    private void setTime(Event entity, @Param("times") String times) {
        if (times.length() == 5) {
            entity.setTime(Time.valueOf(times + ":00"));
        } else {
            entity.setTime(Time.valueOf(times));
        }
    }
}
