package com.dino.sshop._infrastructure.config;

import com.dino.sshop._domain.identity.model.entity.Account;
import com.dino.sshop._domain.identity.model.entity.Token;
import com.dino.sshop._domain.identity.repository.IAccountDomainRepository;
import com.dino.sshop._domain.identity.repository.ITokenDomainRepository;
import com.dino.sshop._infrastructure.security.provider.ISecurityInfraProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashSet;

@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AppInitConfig {

    @Bean
    ApplicationRunner applicationRunner(
            IAccountDomainRepository accountDomainRepo,
            ITokenDomainRepository tokenDomainRepo,
            ISecurityInfraProvider securityInfraProvider
    ) {
        return args -> {
            if (accountDomainRepo.findByUsername("admin").isEmpty()) {
                //todo: use the ddd factory
                HashSet<String> roles = new HashSet<>(Collections.singletonList(Account.RoleType.ADMIN.name()));
                String password = securityInfraProvider.hashPassword("123456");

                Account account = Account.builder()
                        .username("admin")
                        .email("admin@sshop.com")
                        .password(password)
                        .roles(roles)
                        .build();

                Token token = Token.builder()
                        .account(account)
                        .build();

                account.setToken(token);

                accountDomainRepo.save(account);

                log.warn("An admin account have been created with password 123456. Please change it!");
            }
        };
    }
}
