package com.selltickets.example.services;

import com.selltickets.example.models.Event;
import java.util.List;

public interface EventService {
    Event save(Event event);
    List<Event> getAll();
    void delete(Long id);
    Event getEventByName(String name);
    Event getEventById(Long id);

}
