package com.selltickets.example.repositories;

import com.selltickets.example.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long>{
    Ticket getById(Long id);
}
