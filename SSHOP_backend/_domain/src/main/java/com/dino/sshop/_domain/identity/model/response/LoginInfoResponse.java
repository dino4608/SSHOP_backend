package com.dino.sshop._domain.identity.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginInfoResponse {
    String username;
    String maskedEmail;
    String maskedPhone;
    String maskedPassword;
}
