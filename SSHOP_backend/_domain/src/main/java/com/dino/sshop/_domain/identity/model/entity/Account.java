package com.dino.sshop._domain.identity.model.entity;

import com.dino.sshop._domain.common.base.BaseEntity;
import com.dino.sshop._domain.shopping.model.entity.Address;
import com.dino.sshop._domain.shopping.model.entity.Cart;
import com.dino.sshop._domain.shopping.model.entity.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "accounts")
@DynamicInsert // ignore null-value attributes
@DynamicUpdate
@SQLDelete(sql = "UPDATE accounts SET deleted = true WHERE account_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "accountId", updatable = false, nullable = false)
    String id;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    Token token;

    @OneToOne(mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    Shop shop;

    @OneToOne(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    Cart cart;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    List<Address> addresses;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    List<Order> orders;

    String status;

    @Column(nullable = false)
    Set<String> roles;

    @Column(nullable = false, unique = true)
    String username;

    String password;

    String email;

    String phone;

    String name;

    String photo;

    Date dob;

    String gender;

    //THE NESTED OBJECTS//
    public enum RoleType {ADMIN, SELLER, BUYER,}

    public enum GenderType {MALE, FEMALE,}

    public enum StatusType {LACK_INFO, LIVE, DEACTIVATED, SUSPENDED, DELETED,}
}
