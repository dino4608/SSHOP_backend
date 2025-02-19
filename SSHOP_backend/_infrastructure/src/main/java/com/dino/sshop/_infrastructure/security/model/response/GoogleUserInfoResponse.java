package com.dino.sshop._infrastructure.security.model.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleUserInfoResponse {
    private String id;
    private String email;
    private boolean verifiedEmail;
    private String name;
    private String givenName;
    private String familyName;
    private String link;
    private String picture;
    private String gender;
    private String locale;
}
