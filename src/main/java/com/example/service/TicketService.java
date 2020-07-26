package com.example.service;
import com.example.repository.TicketRepository;
import com.example.repository.ContactRepository;
import com.example.model.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;

@Service
@Transactional

public class TicketService {

    private TicketRepository ticketRepository;
    private ContactRepository contactRepository;
    @Autowired
    public TicketService(TicketRepository ticketRepository,ContactRepository contactRepository)
    {
        this.contactRepository=contactRepository;
        this.ticketRepository=ticketRepository;
    }
    public synchronized ResponseEntity create(TicketEntity ticket)
    {
        ticketRepository.saveAndFlush(ticket);
        return new ResponseEntity<>(ticket,HttpStatus.OK);
    }
    public synchronized TicketEntity delete(int id)
    {
        ticketRepository.deleteById(id);
        return null;
    }

    public synchronized  TicketEntity update(TicketEntity ticket,int id)
    {
        if(!ticketRepository.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"ticket does not exist");
        }
        ticketRepository.save(ticket);
        return ticket;
    }
    public synchronized ResponseEntity read(int id)
    {
        System.out.println(ticketRepository.getOne(id));
        TicketEntity ticket=ticketRepository.getOne(id);
       return new ResponseEntity(ticketRepository.getOne(id),HttpStatus.OK);

    }

}

