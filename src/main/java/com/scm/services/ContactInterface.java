package com.scm.services;

import com.scm.entity.Contact;

import java.util.List;

public interface ContactInterface {

    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact> getAll();
    Contact getById(String id);
    void delete(String id);
    List<Contact> search(String name,String email,String phoneNumber);
    List<Contact> getByUserId(String userId);


}
