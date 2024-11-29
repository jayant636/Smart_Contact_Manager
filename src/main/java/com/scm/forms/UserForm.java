package com.scm.forms;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Username is required")
    private String name;

    @Email(message = "Inavlid Email Address")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "About is required")
    private String about;

    @Size(min=8,max=10,message = "Invalid phone number")
    @NotBlank(message = "PhoneNumber is required")
    private String phoneNumber;
}
