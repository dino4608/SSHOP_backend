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

import java.util.List;

@Entity
@Table(name = "categories")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE categories SET deleted = true WHERE category_id = ?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "categoryId", updatable = false, nullable = false)
    String id;

    @OneToMany(mappedBy = "category", fetch =  FetchType.LAZY)
    @JsonIgnore
    List<Product> products;

    @Column(length = 40, nullable = false, unique = true)
    String name;

    String description;

    String photo;

    Integer position;
}
