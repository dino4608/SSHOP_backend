package com.dino.sshop._domain.shopping.model.entity;

import com.dino.sshop._domain.common.base.BaseEntity;
import com.dino.sshop._domain.inventory.model.entity.Sku;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "cart_items")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE cart_items SET deleted = true WHERE item_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "itemId", updatable = false, nullable = false)
    String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", updatable = false, nullable = false)
    @JsonIgnore
    Cart cart;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sku_id", nullable = false)
    Sku sku;

    int quantity;
}
