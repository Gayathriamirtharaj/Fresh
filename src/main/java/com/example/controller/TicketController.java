package com.example.controller;
import com.example.model.TicketEntity;
import com.example.repository.TicketRepository;
import com.example.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/v1/freshdesk")

public class TicketController {

    @Autowired
    private TicketService ticketService;

    private TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository=ticketRepository;
    }

    @PostMapping("/ticket")
    public ResponseEntity create(@RequestBody TicketEntity ticket)
    {
        return (ResponseEntity) ticketService.create(ticket);
        //throw new ResponseStatusException(HttpStatus.OK,"created success");
    }

    @RequestMapping(value="/ticket/{id}",method=RequestMethod.GET)
    public ResponseEntity read(@PathVariable  Integer id)
    {
         return ticketService.read(id);

    }

    @PutMapping("/ticket/{id}")
    public TicketEntity update(@RequestBody TicketEntity ticket,@PathVariable Integer id)
    {
        return ticketService.update(ticket,id);
    }

    @DeleteMapping("/ticket/{id}")
    public TicketEntity delete(@PathVariable Integer id)
    {
        return ticketService.delete(id);
    }

    @RequestMapping(value = "/getAllTickets", method = RequestMethod.GET)
    public List<TicketEntity> getAllTickets(){
        System.out.println(new ArrayList<TicketEntity>(ticketRepository.findAll()));
        return new ArrayList<TicketEntity>(ticketRepository
                .findAll());
    }

}
