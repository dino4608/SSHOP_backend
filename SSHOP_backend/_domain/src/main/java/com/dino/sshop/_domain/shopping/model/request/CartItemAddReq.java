package com.dino.sshop._domain.shopping.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemAddReq {
        SkuReq sku;
        int quantity;

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE)
        public static class  SkuReq {
                String id;
        }
}
