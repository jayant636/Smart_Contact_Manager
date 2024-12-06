package com.scm.services;

import com.scm.entity.Contact;
import com.scm.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactService implements ContactInterface {

    @Autowired
    private ContactRepository contactRepository;


    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepository.findById(id).orElseThrow(()-> new RuntimeException("Contact not found"));
    }

    @Override
    public void delete(String id) {
        var contact = contactRepository.findById(id).orElseThrow(()-> new RuntimeException("Contact does not exist"));
        contactRepository.delete(contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        return List.of();
    }

    @Override
    public List<Contact> getByUserId(String userId) {
      return  contactRepository.findByUserId(userId);
    }
}
