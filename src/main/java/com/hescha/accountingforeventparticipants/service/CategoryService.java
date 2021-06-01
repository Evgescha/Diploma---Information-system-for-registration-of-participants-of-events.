package com.hescha.accountingforeventparticipants.service;

import com.hescha.accountingforeventparticipants.entity.Category;
import com.hescha.accountingforeventparticipants.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends CrudImpl<Category> {

    public CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Category findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

}
