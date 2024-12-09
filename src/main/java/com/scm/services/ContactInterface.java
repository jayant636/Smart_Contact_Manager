package com.scm.services;

import com.scm.entity.Contact;
import com.scm.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContactInterface {

    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact> getAll();
    Contact getById(String id);
    void delete(String id);
    Page<Contact> searchByName(String name , int page,int size,String sortBy , String order,UserEntity user);
    Page<Contact> searchByEmail(String email, int page,int size,String sortBy , String order,UserEntity user);
    Page<Contact> searchByPhoneNumber(String phoneNumber, int page,int size,String sortBy , String order,UserEntity user);

    List<Contact> getByUserId(String userId);
    Page<Contact> getByUser(UserEntity userEntity, int page , int size, String sortField , String sortDirection);
}
