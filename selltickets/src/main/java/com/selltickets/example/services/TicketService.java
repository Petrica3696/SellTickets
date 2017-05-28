package com.selltickets.example.services;

import com.selltickets.example.models.Ticket;

public interface TicketService {
    Ticket save(Ticket ticket);
    void delete(Long id);
    Ticket getTicket(Long id);
}