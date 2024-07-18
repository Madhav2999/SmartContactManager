package com.icwd.serviceregistry.scm20.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactForm
{
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required [example@gmail.com]")
    @Email(message = "Email is invalid")
    private String email;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotBlank(message = "Address is required")
    private String address;
    private String description;
    private boolean favourite;

    private String websiteLink;

    private String linkedInLink;

    private MultipartFile contactImage;

    private String picture;
}
