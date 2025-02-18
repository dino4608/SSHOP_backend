package com.dmon.sshop._domain.shopping.model.entity;

import com.dmon.sshop._domain.common.base.BaseEntity;
import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.model.entity.Shop;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.Instant;
import java.util.ArrayList;

@Entity
@Table(name = "orders")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE order_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "orderId", nullable = false, updatable = false)
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyerId", updatable = false, nullable = false)
    @JsonIgnore
    Account buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId", updatable = false, nullable = false)
    @JsonIgnore
    Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    @JsonIgnore
    Address address;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    ArrayList<OrderItem> items;

    String status;

    int count;

    float subtotal;

    float shippingFee;

    float discount; // seller discount + platform discount

    float shippingDiscount;

    float total;

    Instant orderDate;

    String paymentMethod;

    Instant paymentTime;

    Instant shipmentDate;

    Instant deliveryDate;

    //NESTED OBJECTS//
    public enum StatusType {DRAFT, UNPAID, PREPARING, TRANSIT, DELIVERING, DELIVERED, RETURN, CANCELED,}

    public enum PaymentMethodType {COD, ZALOPAY, MONO, VNPAY,}

}
