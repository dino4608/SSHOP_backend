package com.dino.sshop._domain.shopping.mapper;

import com.dino.sshop._domain.shopping.model.entity.Order;
import com.dino.sshop._domain.shopping.model.request.OrderCheckoutReq;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrderMapper {
    Order toEntity(OrderCheckoutReq.OrderReq OrderByShopReq);
}
