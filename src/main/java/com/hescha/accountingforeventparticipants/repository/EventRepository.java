package com.hescha.accountingforeventparticipants.repository;

import com.hescha.accountingforeventparticipants.entity.Event;
import com.hescha.accountingforeventparticipants.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByNameIgnoreCase(String name);

    List<Event> findByDate(Date date);

    List<Event> findTop2ByDateAfter(Date dateAfter);

    List<Event> findByUsersContainingAndDateBefore(User user, Date dateBefore);

    List<Event> findByUsersContainingAndDateAfter(User user, Date dateBefore);
}
