package com.dmon.sshop._domain.shopping.model.entity;

import com.dmon.sshop._domain.identity.model.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "addresses")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE addresses SET deleted = true WHERE address_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "addressId", updatable = false, nullable = false)
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyerId", updatable = false, nullable = false)
    @JsonIgnore
    Account buyer;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    List<Order> orders;

    String status;

    String contactName;

    String contactPhone;

    String province;

    String district;

    String commune;

    String street;

    boolean isDefault;

    //NESTED OBJECTS//
    public enum StatusType {
        NEW, USED, ARCHIVED
    }
}
