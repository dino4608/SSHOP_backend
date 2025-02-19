package com.dino.sshop._infrastructure.security.provider.impl;

import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import com.dino.sshop._domain.identity.model.entity.Account;
import com.dino.sshop._infrastructure.security.model.enums.TokenType;
import com.dino.sshop._infrastructure.security.provider.ISecurityInfraProvider;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor //create a constructor for final and @NonNull fields
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SecurityInfraProviderImpl implements ISecurityInfraProvider {

    @NonFinal
    @Value("${jwt.access.secret-key}")
    String ACCESS_SECRET_KEY;

    @NonFinal
    @Value("${jwt.refresh.secret-key}")
    String REFRESH_SECRET_KEY;

    @NonFinal
    @Value("${jwt.access.valid-duration}")
    Long ACCESS_VALID_DURATION;

    @NonFinal
    @Value("${jwt.refresh.valid-duration}")
    Long REFRESH_VALID_DURATION;

    PasswordEncoder passwordEncoder;

    //PASSWORD//
    @Override
    public String hashPassword(String plain) {
        return this.passwordEncoder.encode(plain);
    }

    @Override
    public boolean matchPassword(String plain, String hash) {
        return this.passwordEncoder.matches(plain, hash);
    }

    //JWT//
    @Override
    public String genToken(Account account, TokenType tokenType) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getId())
                .issuer("sshop.dino.com")
                .issueTime(new Date())
                .expirationTime(new Date(this.getExpirationDate(tokenType).toEpochMilli()))
                .claim("scope", this.buildScope(account.getRoles()))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(this.getSecretKey(tokenType).getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Jwt decodeToken(String token, TokenType tokenType) {
        if (!tokenType.equals(TokenType.REFRESH_TOKEN))
            throw new AppException(ErrorCode.SYSTEM__KEY_UNSUPPORTED);

        SecretKeySpec secretKeySpec = new SecretKeySpec(this.REFRESH_SECRET_KEY.getBytes(), "HS512");
        JwtDecoder jwtDecoder = NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();

        Jwt jwt = jwtDecoder.decode(token);
        return jwt;
    }

    @Override
    public Duration getValidDuration(TokenType tokenType) {
        switch (tokenType) {
            case REFRESH_TOKEN -> {
                return Duration.ofDays(this.REFRESH_VALID_DURATION);
            }
            case ACCESS_TOKEN -> {
                return Duration.ofDays(this.ACCESS_VALID_DURATION);
            }
            default -> throw new AppException(ErrorCode.SYSTEM__KEY_UNSUPPORTED);
        }
    }

    @Override
    public Instant getExpirationDate(TokenType tokenType) {
        switch (tokenType) {
            case REFRESH_TOKEN -> {
                return Instant.now().plus(this.REFRESH_VALID_DURATION, ChronoUnit.DAYS);
            }
            case ACCESS_TOKEN -> {
                return Instant.now().plus(this.ACCESS_VALID_DURATION, ChronoUnit.DAYS);
            }
            default -> throw new AppException(ErrorCode.SYSTEM__KEY_UNSUPPORTED);
        }
    }

    @Override
    public String getSecretKey(TokenType tokenType) {
        switch (tokenType) {
            case REFRESH_TOKEN -> {
                return this.REFRESH_SECRET_KEY;
            }
            case ACCESS_TOKEN -> {
                return this.ACCESS_SECRET_KEY;
            }
            default -> throw new AppException(ErrorCode.SYSTEM__KEY_UNSUPPORTED);
        }
    }

    /**
     * Get the subject, a claim of jwt payload
     *
     * @return the login of the current user.
     */
    @Override
    public String getAccountId() {
        //1. get Security Context
        SecurityContext securityContext = SecurityContextHolder.getContext();

        //2. get the subject, a claim of jwt payload
        Optional<String> subject = Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));

        return subject.orElseThrow(() -> new AppException(ErrorCode.SECURITY__NOT_IN_SECURITY));
    }


    //HELPER//

    /**
     * extract the subject, a claim of jwt payload, from jwt principal
     *
     * @param authentication: Authentication
     * @return String
     */
    private String extractPrincipal(Authentication authentication) {
        //Code: authentication.getPrincipal() instanceof Jwt jwt
        //Mean: if authentication.getPrincipal() has the Jwt type, assign it to the jwt variable
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getSubject();
        } else if (authentication.getPrincipal() instanceof String s) {
            return s;
        }
        return null;
    }

    /**
     * build scope, a claim of jwt payload
     *
     * @param roles: Set<String>
     * @return String: example "ADMIN_SELLER_USER"
     */
    private String buildScope(Set<String> roles) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(roles))
            roles.forEach(stringJoiner::add);

        return stringJoiner.toString();
    }
}
