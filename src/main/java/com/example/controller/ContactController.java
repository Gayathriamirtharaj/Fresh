package com.example.controller;
import com.example.model.ContactEntity;
import com.example.model.TicketEntity;
import com.example.repository.ContactRepository;
import com.example.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/v1/freshdesk")
public class ContactController {
    @Autowired
    private ContactService contactService;
    private ContactRepository contactRepository;
    @Autowired
    public ContactController(ContactRepository contactRepository)
    {
        this.contactRepository=contactRepository;
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




}
