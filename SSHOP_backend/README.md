# E-commerce RESTful API Backend

This is a RESTful API built with Spring Boot 3 for an e-commerce platform similar to TikTok Shop. It supports various
functionalities such as product management, user management, order processing, and more // todo: continue README.

## How to run

- java -jar SSHOP_backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=[dev]

## Project

- Spring Boot 3.4.0 (Rest, Data JPA, Security)
- PostgreSQL 16.6.0
- Maven
- Yaml
- Jar
- Server: localhost. Port: 8044. Context-path: sshop

Api document

- The project uses Swagger from Open-api to manage api list. Link: http://localhost:8044/sshop/swagger-ui/index.html
- Postman: //todo

Git rule

- Format:
    - \<action>: \<feature>: \<description>
- Action:
    - n: new
    - f: fix
    - g: git
    - r: refactor
    - c: chore

Todo rule:

- Format:
    - //todo: \<priority>: \<description>
- Priority:
    - p1: urgent, important
    - p2: urgent, not important
    - p3: not urgent, important
    - p4: not urgent, not important

Reference

- Spring Security: https://docs.spring.io/spring-security/reference/
- Spring Data JPA: https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#reference
- JPQL: https://docs.oracle.com/html/E13946_04/ejb3_langref.html

## Domain-driven design approach

Business strategy

- Business logic
- Business rule
- Domain
- Boundary domain (boundary context)
- Entity
- Value object
- Boundary aggregate

Collaboration

- Package by layer
- Package by feature
- Multi modules
- Single module

Tactical Design

- Api layer
    - rest
    - rpc
- Application layer
    - aop
    - service
    - external
- Domain layer
    - common
    - domain 1
        - service
        - factory
        - helper
        - repository
        - model
        - mapper
- Infrastructure layer
    - common
    - config
    - technique 1

## Development Environment

- Windows OS v23H2
- Java Development Kit v17 (aka JDK)
- IntelliJ IDEA v2023.3.6 (plus plugins: Lombok, Vuesion Theme)
- Docker v23.0.3 (contains images: Postgres v13.18.bookworm)
- DBeaver DBMS v24.0.3

## Todo

Access

- Control by refresh token
- Detect leak tokens
- Try to log in max 3 times
- Check expiration of token to log out (at FE)
- Refresh page and keep state (at FE)

Category

- Drag and drop to update the position

App

- Recode the aop following Mr.Robin

## Note

Intellij IDEA

- Open/close the terminal: Alt + F12
- Open/close the run: Alt + 4
- Find a file: Ctrl + Shift + N
- Find anything: Double Shift
- Clear redundant imports: Ctrl + Alt + O
- Replace all on a file: Ctrl + R
- Replace all on whole project: Ctrl + Shift + R
- Exit the insert mode in git: Ctrl + C
- Reformat code: Ctrl + Alt + L

Reference

- Spring Security: https://docs.spring.io/spring-security/reference/
- Spring Data JPA: https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#reference
- JPQL: https://docs.oracle.com/html/E13946_04/ejb3_langref.html

Java

- int vs Integer: non-null or nullable

Spring Data JPA

- Methods
- Query
    - use JPQL command, allow to pass params
    - allow to Select, Modify (can update, delete; but can't create)

Spring Security:

- Authentication base on JWT
- Token follows the OAuth2 standard to Spring Security map claims of payload to fields of authentication
    - subject to name
    - scope to authorities[].authority
        - authority have a default prefix "SCOPE_", then system will config to convert to "ROLE_", example: ROLE_ADMIN
- Authorization base on Role
    - use the hasAuthority("ROLE_ADMIN") or hasRole("ADMIN") methods at Security Filter Chain tier
    - use the PreAuthority, PostAuthority annotation at Methods of Service tier
- Handle exception at Security Filter Chain tier

Spring Validate:

- @NotNull: not null
- @NotEmpty: not null, length/size is greater than 0
- @NotBlank: not null, the trimmed length/size is greater than 0

Yaml: is a config file which includes environment variables

- follows tree and key-value structures
- provides various data types (int, string, boolean, map, ...)
- configs at multiple environments (dev, test, pre-release, product)
  Maven: is a project management tool, has xml type
- Group: an implementing organization. Ex: com.dino
- Artifact: an unique project: Ex: SSHOP_backend
- Name: an application main. Ex: SshopSpringbootBackendApplication or SshopApplication
- Package name: an deepest package contain the source code. Ex: SSHOP_backend => com.dino.SSHOP_backend, Sshop =>
  com.dino.sshop

Jar: is a format used for packaging and deploying applications

- run command: java -jar SSHOP_backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=[dev]