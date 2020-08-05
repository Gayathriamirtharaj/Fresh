package com.example.service;
import com.example.model.ContactEntity;
import com.example.vo.*;
import com.example.repository.AgentRepository;
import com.example.repository.TicketRepository;
import com.example.repository.ContactRepository;
import com.example.model.TicketEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
@Transactional

public class TicketService {

    private static TicketRepository ticketRepository;
    private final ContactRepository contactRepository;
    private AgentRepository agentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public TicketService(TicketRepository ticketRepository, ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
        this.ticketRepository = ticketRepository;
    }

    public synchronized Object create(TicketEntity ticket) {
        TicketEntity ticketEntity = modelMapper.map(ticket, TicketEntity.class);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            ticketEntity.setUsername(username);
            ticketRepository.saveAndFlush(ticket);}

            if (!ContactRepository.existsByEmail(ticket.getEmail())) {
                Contacts contact = new Contacts();
                contact.setContacts(ticket.getContacts());
                contact.setEmail(ticket.getEmail());
                ContactEntity contactEntity = modelMapper.map(contact, ContactEntity.class);
                contactRepository.save(contactEntity);

            }
            return new ResponseEntity<>(ticket, HttpStatus.OK);


        }
        public synchronized TicketEntity delete (int id)
                {
                        ticketRepository.deleteById(id);
        return null;
    }

        public synchronized TicketEntity update (TicketEntity ticket,int id)
        {
            if (!ticketRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "ticket does not exist");
            }
            ticketRepository.save(ticket);
            return ticket;
        }
        public static synchronized ResponseEntity read(int id)
                {
                        System.out.println(ticketRepository.getOne(id));
        TicketEntity tickets = ticketRepository.getOne(id);
        return new ResponseEntity(ticketRepository.getOne(id), HttpStatus.OK);

    }
    public synchronized ArrayList<TicketEntity> list(int assigneeId)
    {
        return new ArrayList<TicketEntity>(ticketRepository.findByassigneeId(assigneeId));

    }




    }


