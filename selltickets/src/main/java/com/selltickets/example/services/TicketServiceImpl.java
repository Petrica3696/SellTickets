package com.selltickets.example.services;

import com.selltickets.example.models.Ticket;
import com.selltickets.example.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    private TicketRepository repository;

    @Override
    public Ticket save(Ticket ticket) {
        return this.repository.save(ticket);
    }

    @Override
    public void delete(Long id) {
        this.repository.delete(id);
    }

    @Override
    public Ticket getTicket(Long id) {
        return this.repository.getById(id);
    }
}
