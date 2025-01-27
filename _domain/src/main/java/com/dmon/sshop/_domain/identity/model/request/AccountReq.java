package com.dmon.sshop._domain.identity.model.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

public class AccountReq {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Create {
        @Size(message = "ACCOUNT__USERNAME_MIN", min = 4)
        String username;

        @Pattern(message = "ACCOUNT__EMAIL_NOT_MATCHED", regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$")
        String email;

        @Pattern(message = "ACCOUNT__PHONE_NOT_MATCHED", regexp = "^[0-9]{10}$")
        String phone;

        @Size(message = "ACCOUNT__PASSWORD_MIN", min = 6)
        String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Update {
        String email;
        String phone;
        String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UsernameAuthenticateRequest {
        String username;
        String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Oauth2AuthenticateRequest {
        String code;
        String oauth2Type;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Signup {
        String username;
        String password;
    }
}
