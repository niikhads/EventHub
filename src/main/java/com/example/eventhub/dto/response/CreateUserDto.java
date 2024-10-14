package com.example.eventhub.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserDto
{
    @NotBlank(message = "Username  can not be null or empty")
    String fullName;

    @Email(message = "Email  can not be null or empty")
    String email;

    @NotBlank(message = "Password  can not be null or empty")
    String password;

    @NotBlank(message = "Password  can not be null or empty")
    String confirmPassword;
}
