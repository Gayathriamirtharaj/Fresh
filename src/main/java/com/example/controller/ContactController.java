package com.example.controller;
import com.example.model.AgentEntity;
import com.example.model.ContactEntity;
import com.example.model.Role;
import com.example.model.TicketEntity;
import com.example.repository.AgentRepository;
import com.example.repository.TicketRepository;
import com.example.service.TicketService;
import com.example.vo.Agent;
import com.example.vo.DataCollection;
import com.example.repository.ContactRepository;
import com.example.service.ContactService;
import com.example.vo.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/v1/freshdesk")
public class ContactController {
    @Autowired
    private ContactService contactService;
    private TicketService ticketService;
    private final ContactRepository contactRepository;
    private final TicketRepository ticketRepository;
    private final AgentRepository agentRepository;
    TicketEntity ticket;
    @Autowired
    public ContactController(ContactRepository contactRepository,TicketRepository ticketRepository,AgentRepository agentRepository)
    {
        this.contactRepository=contactRepository;
        this.ticketRepository=ticketRepository;
        this.agentRepository=agentRepository;
    }

    @PostMapping("/contact")
    public ResponseEntity create(@RequestBody ContactEntity contact)
    {
        return contactService.create(contact);
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity read(@PathVariable Integer id)
    {
        return contactService.read(id);
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity update(@RequestBody ContactEntity contact,@PathVariable Integer id)
    {
        return contactService.update(contact,id);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity delete(@PathVariable Integer id)
    {
        return contactService.delete(id);
    }

    @RequestMapping(value = "/getAllContacts", method = RequestMethod.GET)
    public ArrayList<ContactEntity> getAllTickets(){
        System.out.println(new ArrayList<ContactEntity>(contactRepository.findAll()));
        return new ArrayList<ContactEntity>(contactRepository
                .findAll());
    }
    @GetMapping("/tickets")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_AGENT')")
    public ResponseEntity<DataCollection> readtickets(int id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("username: " + username);
        System.out.println("authorities: " + authorities);

        DataCollection dataCollection = new DataCollection<>();
        if (authorities.contains(new SimpleGrantedAuthority(Role.ROLE_ADMIN.name()))) {
            System.out.println(new ArrayList<TicketEntity>(ticketRepository.findAll()));
            System.out.println(new ArrayList<ContactEntity>(contactRepository.findAll()));
            System.out.println(new ArrayList<AgentEntity>(agentRepository.findAll()));
            return new ResponseEntity<>((DataCollection) ticketRepository.findAll(), HttpStatus.OK);}

             else if(authorities.contains(new SimpleGrantedAuthority(Role.ROLE_USER.name()))) {

            final ResponseEntity responseEntity = new ResponseEntity(TicketService.read(id), HttpStatus.OK);
            return responseEntity;

        }
             else if(authorities.contains(new SimpleGrantedAuthority(Role.ROLE_AGENT.name())))
        {
                ticketService.delete(id);
                ticketService.create(ticket);
                ticketService.update(ticket,id);
        }
       return null;

    }
}
