package com.selltickets.example.controllers;

import com.selltickets.example.models.Event;
import com.selltickets.example.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Event>> get(){
        List<Event> events = this.eventService.getAll();
        if(events.isEmpty()){
            return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<String> newEvent(Event event){
        Event eventExists=eventService.getEventById(event.getId());
        if(eventExists!=null){
            return new ResponseEntity<String>("Event already exists",HttpStatus.CONFLICT);
        }
        else {
            eventService.save(event);
            return new ResponseEntity<String>("Event created", HttpStatus.OK);
        }
    }

}
