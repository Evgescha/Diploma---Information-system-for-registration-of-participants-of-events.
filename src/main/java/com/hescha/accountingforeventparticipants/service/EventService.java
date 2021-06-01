package com.hescha.accountingforeventparticipants.service;

import com.hescha.accountingforeventparticipants.entity.Event;
import com.hescha.accountingforeventparticipants.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService extends CrudImpl<Event> {

    public EventRepository repository;

    @Autowired
    public EventService(EventRepository repository) {
        super(repository);
        this.repository = repository;
    }


}
