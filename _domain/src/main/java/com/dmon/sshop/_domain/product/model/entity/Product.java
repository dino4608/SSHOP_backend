package com.dmon.sshop._domain.product.model.entity;

import com.dmon.sshop._domain.common.base.BaseEntity;
import com.dmon.sshop._domain.identity.model.entity.Shop;
import com.dmon.sshop._domain.inventory.model.entity.Sku;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE product_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "productId", updatable = false, nullable = false)
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId", updatable = false, nullable = false)
    Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    Category category;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    ProductMetric metric; //todo: edit the api, creating a product

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Sku> skus; //orphanRemoval: xóa các child mồ coi

    String status;

    @Column(nullable = false)
    String name;

    @Column(columnDefinition = "text")
    String description;

    String slug;

    @Column(nullable = false)
    String thumb;

    List<String> photos;

    String video;

    String sizeChart;

    Float retailPrice;

    Float weight; // unit in grams (g)

    String location;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    ArrayList<Attribute> attributes;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    ArrayList<TierVariation> tierVariations;

    //NESTED OBJECTS//
    public enum StatusType {DRAFT, REVIEWING, LIVE, DEACTIVATED, SUSPENDED, DELETED,}

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Attribute {
        String name;

        String value;

        String link;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TierVariation {
        String name;

        ArrayList<String> options;

        ArrayList<String> photos;
    }
}
