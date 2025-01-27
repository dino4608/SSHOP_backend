package com.dmon.sshop._domain.shopping.model.response;

import com.dmon.sshop._domain.shopping.model.entity.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCheckoutRes {

    ArrayList<Order> orders;
    SummaryRes summary;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SummaryRes {

        @Builder.Default
        float subtotal = 0;

        @Builder.Default
        float shippingFee = 0;

        @Builder.Default
        float discount = 0;

        @Builder.Default
        float shippingDiscount = 0;

        @Builder.Default
        float total = 0;
    }

}
