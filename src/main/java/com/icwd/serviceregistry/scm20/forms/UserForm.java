package com.icwd.serviceregistry.scm20.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm
{
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Minimum 3 characters required")
    private String name;

    @NotBlank(message = "email is required")
    @Size(min = 10, message = "Minimum 10 characters required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 10, message = "Minimum 10 characters required")
    private String password;

    @NotBlank(message = "About is required")
    @Size(min = 10, message = "Minimum 10 characters required")
    private String about;

    @NotBlank(message = "PhoneNumber is required")
    @Size(min = 10, message = "Minimum 10 characters required")
    private String phoneNumber;
}
