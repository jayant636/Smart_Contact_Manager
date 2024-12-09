package com.scm.services;

import com.scm.entity.Contact;
import com.scm.entity.UserEntity;
import com.scm.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Page<Contact> searchByName(String name, int page, int size, String sortBy, String order, UserEntity user) {
        Sort sort = order.equals("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page,size,sort);
        return contactRepository.findByUserAndNameContaining(user,name,pageable);
    }

    @Override
    public Page<Contact> searchByEmail(String email, int page, int size, String sortBy, String order, UserEntity user) {
        Sort sort = order.equals("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page,size,sort);
        return contactRepository.findByUserAndEmailContaining(user,email,pageable);
    }

    @Override
    public Page<Contact> searchByPhoneNumber(String phoneNumber, int page, int size, String sortBy, String order,UserEntity user) {
        Sort sort = order.equals("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page,size,sort);
        return contactRepository.findByUserAndPhoneNumberContaining(user,phoneNumber,pageable);
    }


    @Override
    public List<Contact> getByUserId(String userId) {
      return  contactRepository.findByUserId(userId);
    }

    @Override
    public Page<Contact> getByUser(UserEntity userEntity, int page , int size , String sortBy , String direction) {
        Sort sort = direction.equals("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page,size,sort);

      return   contactRepository.findByUser(userEntity,pageable);
    }
}
