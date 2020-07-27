package com.example.controller;
import com.example.model.TicketEntity;
import com.example.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin("http://localhost:4200")
@RequestMapping("/v1/freshdesk/admin")
public class AdminController {
    private TicketRepository ticketRepository;
    @Autowired
    public AdminController(TicketRepository ticketRepository)
    {
        this.ticketRepository=ticketRepository;
    }
    @RequestMapping(value = "/getAllTickets", method = RequestMethod.GET)
    public List<TicketEntity> getAllTickets(){
        System.out.println(new ArrayList<TicketEntity>(ticketRepository.findAll()));
        return new ArrayList<TicketEntity>(ticketRepository
        .findAll());
    }


}
