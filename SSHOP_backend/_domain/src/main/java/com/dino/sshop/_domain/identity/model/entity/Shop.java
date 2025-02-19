package com.dino.sshop._domain.identity.model.entity;

import com.dino.sshop._domain.common.base.BaseEntity;
import com.dino.sshop._domain.product.model.entity.Product;
import com.dino.sshop._domain.shopping.model.entity.Order;
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
@Table(name = "shops")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE shops SET deleted = true WHERE account_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Shop extends BaseEntity {

    @Id
    String id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId", updatable = false, nullable = false)
    @JsonIgnore
    @ToString.Exclude
    Account seller;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    List<Product> products;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    List<Order> orders;

    String status;

    String shopName;

    String shopCode;

    String shopLogo;

    String contactEmail;

    String contactPhone;

    String businessType;

    String sellerType;

    //THE NESTED OBJECTS//
    public enum StatusType {LACK_INFO, REVIEWING, LIVE, DEACTIVATED, SUSPENDED, CLOSED, DELETED,}

    public enum BusinessType {INDIVIDUAL, HOUSEHOLD, ENTERPRISE}
}
