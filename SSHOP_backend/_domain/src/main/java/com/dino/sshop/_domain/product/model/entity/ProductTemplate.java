package com.dino.sshop._domain.product.model.entity;

import com.dino.sshop._domain.common.base.BaseEntity;
import com.dino.sshop._domain.inventory.model.entity.Sku;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.*;

@Entity
@Table(name = "ProductTemplates")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE product_templates SET deleted = true WHERE category_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductTemplate extends BaseEntity {
    @Id
    String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", updatable = false, nullable = false)
    Category category;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    Product.Attribute attribute;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    Sku.Specification specification;
}
