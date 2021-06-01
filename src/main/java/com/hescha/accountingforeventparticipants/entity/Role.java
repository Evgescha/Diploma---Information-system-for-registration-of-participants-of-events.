package com.hescha.accountingforeventparticipants.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Role extends AbstractEntity {

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Collection<User> users = new ArrayList<User>();

    public Role(final String name) {
        super();
        this.name = name;
    }

    public Role(final String name, final Collection<User> users) {
        super();
        this.name = name;
        this.users = users;
    }

    public Role() {
        super();
    }

    @Override
    public String toString() {
        return "Role [name=" + name + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

}
