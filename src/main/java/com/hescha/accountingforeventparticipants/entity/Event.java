package com.hescha.accountingforeventparticipants.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Event extends AbstractEntity {

    private String name;

    @Column(length = 40000, columnDefinition = "TEXT")
    private String description;

    private Date date = new Date(System.currentTimeMillis());

    private Time time = Time.valueOf("00:00:00");

    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<User> users = new HashSet<>();

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", image='" + image + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Event event = (Event) o;

        if (name != null ? !name.equals(event.name) : event.name != null)
            return false;
        if (description != null ? !description.equals(event.description) :
                event.description != null)
            return false;
        if (date != null ? !date.equals(event.date) : event.date != null)
            return false;
        if (time != null ? !time.equals(event.time) : event.time != null)
            return false;
        if (image != null ? !image.equals(event.image) : event.image != null)
            return false;
        return category != null ? category.equals(event.category) :
                event.category == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode()
                : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
