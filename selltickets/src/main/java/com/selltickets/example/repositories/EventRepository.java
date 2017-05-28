package com.selltickets.example.repositories;

import com.selltickets.example.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event getByName(String name);
    Event getById(Long id);
}
