package com.dino.sshop._domain.identity.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Oauth2LoginRequest {
    String code;
    String server;
}
