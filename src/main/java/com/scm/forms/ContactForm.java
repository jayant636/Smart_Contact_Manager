package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ContactForm {
    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "Inavlid Email Address")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "phoneNumber is required")
    private String phoneNumber;
    @NotBlank(message = "Address is required")
    private String address;
    private String description;
    private boolean favourite;
    private String websiteLinks;
    private String linkedInLink;
    private MultipartFile profileImage;

}
