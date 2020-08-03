package com.example.service;
import com.example.repository.UserRepository;
import com.example.model.AgentEntity;
import com.example.model.ContactEntity;
import com.example.repository.AgentRepository;
import com.example.repository.ContactRepository;
import com.example.repository.TicketRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service

public class ContactService {
    private TicketRepository ticketRepository;
    private ContactRepository contactRepository;
    private AgentRepository agentRepository;
    private UserRepository userRepository;
    @Autowired
    public ContactService(ContactRepository contactRepository, AgentRepository agentRepository,TicketRepository ticketRepository,UserRepository userRepository)
    {
        this.contactRepository=contactRepository;
        this.agentRepository=agentRepository;
        this.ticketRepository=ticketRepository;
        this.userRepository=userRepository;
    }
    public synchronized  ResponseEntity makeagent(ContactEntity contact)
    {
        //agentRepository.save(agen);
        return new ResponseEntity(makeagent(contact),HttpStatus.OK);
    }
    public synchronized ResponseEntity create(ContactEntity contact)
    {
        contactRepository.save(contact);
        return new ResponseEntity(contact,HttpStatus.OK);
    }
    public synchronized ResponseEntity  read(int id)
    {
        if(contactRepository.findByID(id)==null)
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        else
        {
            return new ResponseEntity(contactRepository.getOne(id),HttpStatus.OK);
        }
    }
    public synchronized ResponseEntity update(ContactEntity contact,int id)
    {
        if(contactRepository.findByID(id)==null)
        {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else
        {
            contactRepository.save(contact);
            return new ResponseEntity(contact,HttpStatus.OK);
        }
    }
    public ResponseEntity delete(int id)
    {
        if(contactRepository.findByID(id)==null)
        {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else
        {
            contactRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }
    }
    public synchronized ResponseEntity createagent(AgentEntity agent)
    {
        agentRepository.save(agent);
        return new ResponseEntity(agent,HttpStatus.OK);
    }
    public synchronized  ResponseEntity deleteagent(int id)
    {
        if(agentRepository.existsById(id))
        {
            agentRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        else
             return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
   /* public String makeAgent(int id)
    {
        Optional<ContactEntity> contactEntity=contactRepository.findById(id);
        AgentEntity agentEntity=new AgentEntity();
        if(userRepository.existsByUsername(contactEntity)
        {
            agentEntity.setEmail(contactEntity.getEmail());

            agentRepository.save(agentEntity);
            return "Agent created";
        }
        else
        {
            throw new ServiceException("Unable to make Agent",HttpStatus.NOT_FOUND);
        }
    }*/

}
