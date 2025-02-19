package com.dino.sshop._domain.inventory.model.entity;

import com.dino.sshop._domain.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "inventories")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE inventories SET deleted = true WHERE sku_id = ?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Inventory extends BaseEntity {
    @Id
    String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skuId", updatable = false, nullable = false)
    @JsonIgnore
    Sku sku;

    Integer total;

    Integer stocks;

    Integer sales;
}
