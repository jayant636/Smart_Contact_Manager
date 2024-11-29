package com.scm.entity;

import com.scm.enums.Providers;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "User")
public class UserEntity {

    @Id
    private String userId;
    @Column(unique = true,nullable = false)
    private String name;
    @Column(unique = true ,nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String about;
    private String profilePic;
    private String phoneNumber;

    private boolean enabled;
    private boolean emailVerified;
    private boolean phoneVerified;

    private Providers provider=Providers.SELF;
    private String providerUserId;

    // using mappedBy will ensure that table will be created in contact entity
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Contact> contacts = new ArrayList<>();


}
