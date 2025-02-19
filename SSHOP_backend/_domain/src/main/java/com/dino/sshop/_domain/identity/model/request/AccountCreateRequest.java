package com.dino.sshop._domain.identity.model.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreateRequest {

    @Size(message = "ACCOUNT__USERNAME_MIN", min = 4)
    String username;

    @Pattern(message = "ACCOUNT__EMAIL_NOT_MATCHED", regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$")
    String email;

    @Pattern(message = "ACCOUNT__PHONE_NOT_MATCHED", regexp = "^[0-9]{10}$")
    String phone;

    @Size(message = "ACCOUNT__PASSWORD_MIN", min = 6)
    String password;
}
