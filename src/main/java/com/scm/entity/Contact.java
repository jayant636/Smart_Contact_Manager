package com.scm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    private boolean favourite;
    private String websiteLinks;
    private String linkedInLink;
    private String cloudinaryImagePublicId;

    @ManyToOne
    @JsonIgnore
    private UserEntity user;

    @OneToMany(mappedBy = "contact" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<SocialLink> links = new ArrayList<>();


}
