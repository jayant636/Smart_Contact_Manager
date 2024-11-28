package com.scm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Contact")
public class Contact {

    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    private String description;
    private boolean favourite = false;
    private String websiteLinks;
    private String linkedInLink;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "contact" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<SocialLink> links = new ArrayList<>();


}
