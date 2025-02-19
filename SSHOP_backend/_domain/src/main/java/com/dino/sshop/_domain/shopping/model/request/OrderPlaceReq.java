package com.dino.sshop._domain.shopping.model.request;

import com.dino.sshop._domain.common.annotation.IsEnum;
import com.dino.sshop._domain.shopping.model.entity.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderPlaceReq {
    ArrayList<String> orderIds;

    ArrayList<String> cartItemIds;

    String addressId;

    @IsEnum(enumClass = Order.PaymentMethodType.class, message = "ORDER__PAYMENT_METHOD_UNSUPPORTED")
    Order.PaymentMethodType paymentMethod;
}
