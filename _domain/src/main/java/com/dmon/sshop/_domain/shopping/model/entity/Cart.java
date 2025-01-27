package com.dmon.sshop._domain.shopping.model.entity;

import com.dmon.sshop._domain.common.base.BaseEntity;
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
@Table(name = "carts")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE carts SET deleted = true WHERE cart_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart extends BaseEntity {
    @Id
    String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyerId", updatable = false, nullable = false)
    @JsonIgnore
    Account buyer;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<CartItem> items;

    int count;

}
