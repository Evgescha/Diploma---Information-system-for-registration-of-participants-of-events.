package com.hescha.accountingforeventparticipants.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table(name = "myUsers")
@Data
public class User extends AbstractEntity {

    @Column(unique = true)
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String fio;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private Date dateBorn;

    @ManyToMany
    private Set<Event> events = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
        super();
    }

    public List<String> getRoleListNames() {
        List<String> roleNames = new ArrayList<>();
        roleNames.add(role.getName());
        return roleNames;
    }

    public boolean isHaveEvent(Event event) {
        for (Event e : events) {
            if (e.getId() == event.getId())
                return true;
        }

        return false;
    }

}
