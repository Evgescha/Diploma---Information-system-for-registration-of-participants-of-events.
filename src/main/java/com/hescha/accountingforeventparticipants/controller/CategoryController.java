package com.hescha.accountingforeventparticipants.controller;

import java.util.List;

import com.hescha.accountingforeventparticipants.entity.Category;
import com.hescha.accountingforeventparticipants.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    String getCategory(Model model) {
        List<Category> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "category-list";
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {
        if (id != null) {
            Category entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Category());
        }
        return "category-add-edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/category";
    }

    @GetMapping("/id/{id}")
    public String getByTag(Model model, @PathVariable("id") Long id) {
        Category category = service.read(id);
        model.addAttribute("list", category.getEvents());
        model.addAttribute("currentCategory", category);
        return "event";
    }

    @PostMapping("/create")
    public String createOrUpdate(Category entity) {
        service.create(entity);
        return "redirect:/category";
    }
}
