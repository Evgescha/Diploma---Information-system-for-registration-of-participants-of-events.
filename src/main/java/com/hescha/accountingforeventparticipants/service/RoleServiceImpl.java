package com.hescha.accountingforeventparticipants.service;

import com.hescha.accountingforeventparticipants.entity.Role;
import com.hescha.accountingforeventparticipants.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends CrudImpl<Role> {

    public RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Role findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

}
