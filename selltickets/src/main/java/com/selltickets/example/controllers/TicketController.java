package com.selltickets.example.controllers;

import com.selltickets.example.DTO.TicketDTO;
import com.selltickets.example.models.Event;
import com.selltickets.example.models.Ticket;
import com.selltickets.example.services.EventService;
import com.selltickets.example.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/v1/events/{ticketId}",method = RequestMethod.GET)
    public ResponseEntity<TicketDTO> ticketDetails(@PathVariable("ticketId") Long ticketId) {
        Ticket ticket = this.ticketService.getTicket(ticketId);
        Event event;
        TicketDTO ticketDTO = new TicketDTO();
        if (ticket == null) {
            return new ResponseEntity<TicketDTO>(HttpStatus.NO_CONTENT);
        } else {
            event= eventService.getEventById(ticket.getEventId());
            ticketDTO.setBuyerName(ticket.getBuyerName());
            ticketDTO.setEventName(event.getName());
            ticketDTO.setLocation(event.getLocation());
            ticketDTO.setDate(event.getDate());
            ticketDTO.setOrganizer(event.getOrganizer());
            ticketDTO.setPrice(event.getPrice());
            ticketDTO.setTicketId(ticket.getId());
            return new ResponseEntity<TicketDTO>(ticketDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/v1/events/{id}/buyTickets",method = RequestMethod.POST)
    public ResponseEntity<String> buyTicket(@PathVariable("id") Long id,String buyerName){
        Event event = this.eventService.getEventById(id);
        Ticket ticket;
        if(event == null){
            return new ResponseEntity<String>("Acest event nu exista",HttpStatus.NO_CONTENT);
        }
        else if(event.getCapacity()==0)
               return new ResponseEntity<String>("Toate locurile sunt ocupate",HttpStatus.NOT_FOUND);
        else{
            ticket= new Ticket();
            ticket.setBuyerName(buyerName);
            ticket.setEventId(id);
            ticketService.save(ticket);
            event.setCapacity(event.getCapacity()-1);
            eventService.save(event);
        return new ResponseEntity<String>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/v1/events/deleteTicket",method = RequestMethod.POST)
    public ResponseEntity<String> deleteTicket(Long ticketId){
        Ticket ticket = ticketService.getTicket(ticketId);
        if(ticket == null){
            return new ResponseEntity<String>("Acest tichet nu exista",HttpStatus.NO_CONTENT);
        }
        else{
            ticketService.delete(ticketId);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }


}
