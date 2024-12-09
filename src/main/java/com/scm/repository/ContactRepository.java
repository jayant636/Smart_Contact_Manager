package com.scm.repository;

import com.scm.entity.Contact;
import com.scm.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {

    Page<Contact> findByUser(UserEntity userEntity, Pageable pageable);

    @Query("SELECT c FROM Contact c WHERE c.user.id=:userId")
    List<Contact> findByUserId(String userId);

    Page<Contact> findByUserAndNameContaining( UserEntity user,String nameKeyword , Pageable pageable);
    Page<Contact> findByUserAndEmailContaining( UserEntity user,String emailKeyword , Pageable pageable);
    Page<Contact> findByUserAndPhoneNumberContaining( UserEntity user,String PhoneKeyword , Pageable pageable);

}
