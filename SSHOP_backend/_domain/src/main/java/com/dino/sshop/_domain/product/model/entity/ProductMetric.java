package com.dino.sshop._domain.product.model.entity;

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
@Table(name = "ProductMetrics")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE product_metrics SET deleted = true WHERE product_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductMetric extends BaseEntity {
    @Id
    String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", updatable = false, nullable = false)
    @JsonIgnore
    Product product;

    Integer stocks;

    Integer sales;

    Integer views;

    Integer carts;

}
