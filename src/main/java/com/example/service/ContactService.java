package com.example.service;
import com.example.model.ContactEntity;
import com.example.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service

public class ContactService {
    private ContactRepository contactRepository;
    @Autowired
    public ContactService(ContactRepository contactRepository)
    {
        this.contactRepository=contactRepository;
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



}
