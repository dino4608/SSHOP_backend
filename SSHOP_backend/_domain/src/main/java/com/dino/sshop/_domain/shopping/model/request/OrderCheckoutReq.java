package com.dino.sshop._domain.shopping.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCheckoutReq {
    ArrayList<OrderReq> orders;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class OrderReq {
        SellerReq seller;
        ArrayList<OrderItemReq> items;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SellerReq {
        String id;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class OrderItemReq {
        SkuReq sku;
        int quantity;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SkuReq {
        String id;
    }
}
