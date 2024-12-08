package com.scm.services;

import com.scm.entity.Contact;
import com.scm.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactInterface {

    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact> getAll();
    Contact getById(String id);
    void delete(String id);
    List<Contact> search(String name,String email,String phoneNumber);
    List<Contact> getByUserId(String userId);
    Page<Contact> getByUser(UserEntity userEntity, int page , int size, String sortField , String sortDirection);
}
