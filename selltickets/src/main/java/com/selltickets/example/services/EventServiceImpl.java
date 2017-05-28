package com.selltickets.example.services;

import com.selltickets.example.models.Event;
import com.selltickets.example.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository repository;

    @Override
    public Event save(Event event) {
        return this.repository.save(event);
    }

    @Override
    public List<Event> getAll() {
        return this.repository.findAll();
    }

    @Override
    public void delete(Long id) {
        this.repository.delete(id);
    }

    public Event getEventByName(String name){ return this.repository.getByName(name); }

    public Event getEventById(Long id) { return this.repository.getById(id); }
}
