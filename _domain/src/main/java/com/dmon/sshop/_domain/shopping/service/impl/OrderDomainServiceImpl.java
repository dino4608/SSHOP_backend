package com.dmon.sshop._domain.shopping.service.impl;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.service.IShopDomainService;
import com.dmon.sshop._domain.product.service.ISkuDomainService;
import com.dmon.sshop._domain.shopping.factory.OrderFactory;
import com.dmon.sshop._domain.shopping.mapper.IOrderMapper;
import com.dmon.sshop._domain.shopping.model.entity.Address;
import com.dmon.sshop._domain.shopping.model.entity.Order;
import com.dmon.sshop._domain.shopping.model.entity.OrderItem;
import com.dmon.sshop._domain.shopping.model.request.OrderCheckoutReq;
import com.dmon.sshop._domain.shopping.model.request.OrderPlaceReq;
import com.dmon.sshop._domain.shopping.model.response.OrderCheckoutRes;
import com.dmon.sshop._domain.shopping.repository.IOrderDomainRepository;
import com.dmon.sshop._domain.shopping.service.IAddressDomainService;
import com.dmon.sshop._domain.shopping.service.IOrderDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderDomainServiceImpl implements IOrderDomainService {

    IShopDomainService shopDomainService;
    ISkuDomainService skuDomainService;
    IAddressDomainService addressDomainService;
    IOrderDomainRepository orderDomainRepo;
    IOrderMapper orderMapper;

    /**
     * desc:
     * - to checkout an order
     * request:
     * - list of order:
     * - - seller id
     * - - order items
     * - - sku id
     * - - quantity
     * - - user id
     * logic:
     * - for each order:
     * - - seller id: find or error
     * - - user id: reference
     * - - sku ids: find or error, reference
     * - - create order as DRAFT
     * - - save
     */
    public OrderCheckoutRes checkout(OrderCheckoutReq orderCheckoutReq, String userId) {

        ArrayList<Order> ordersRes = new ArrayList<>();
        OrderCheckoutRes.SummaryRes summaryRes = new OrderCheckoutRes.SummaryRes();

        orderCheckoutReq.getOrders().stream().parallel().forEach((orderReq -> {
            Order orderRequested = this.orderMapper.toEntity(orderReq);

            orderRequested.setBuyer(Account.builder().id(userId).build());

            this.shopDomainService.findOrError(orderRequested.getShop().getId());

            ArrayList<OrderItem> itemsRequested = orderRequested.getItems().stream().parallel()
                    .peek((itemRequested) -> itemRequested.setSku(
                            this.skuDomainService.findOrError(itemRequested.getSku().getId())))
                    .collect(Collectors.toCollection(ArrayList::new));
            orderRequested.setItems(itemsRequested);

            Order orderCreated = OrderFactory.createOrder(orderRequested, Order.StatusType.DRAFT);

            Order orderResult = this.orderDomainRepo.save(orderCreated);

            ordersRes.add(orderResult);

            summaryRes.setSubtotal(summaryRes.getTotal() + orderResult.getSubtotal());
            summaryRes.setShippingFee(summaryRes.getShippingFee() + orderResult.getShippingFee());
            summaryRes.setDiscount(summaryRes.getDiscount() + orderResult.getDiscount());
            summaryRes.setShippingDiscount(summaryRes.getShippingDiscount() + orderResult.getShippingDiscount());
            summaryRes.setTotal(summaryRes.getTotal() + orderResult.getTotal());
        }));

        //todo: inventory

        OrderCheckoutRes orderCheckoutRes = OrderCheckoutRes.builder()
                .orders(ordersRes)
                .summary(summaryRes)
                .build();

        return orderCheckoutRes;
    }


    /**
     * desc:
     * - to place an order:
     * request: OrderPlaceReq
     * - list of order id
     * - list of cart item id (optional)
     * - address id (optional)
     * - payment method (optional)
     * logic:
     * - update each order
     * - - status is UNPAID or PREPARING which depends on payment method
     * - - payment method
     * - - address id
     * - delete of cart item id
     * response:
     * - no content
     */
    public Void place(OrderPlaceReq orderPlaceReq) {
        //create order
        this.addressDomainService.findOrError(orderPlaceReq.getAddressId());

        orderPlaceReq.getOrderIds().stream().parallel().forEach((orderId) -> {
            Order orderRequested = this.findOrError(orderId);

            orderRequested.setId(orderId);
            orderRequested.setAddress(Address.builder().id(orderPlaceReq.getAddressId()).build());
            orderRequested.setPaymentMethod(orderPlaceReq.getPaymentMethod().name());

            OrderFactory.createOrder(orderRequested, Order.StatusType.UNPAID);
        });



        //remove cart item

        return null;
    }

    //HELPER//
    public Order findOrError(String orderId) {
        Order orderPresent = this.orderDomainRepo.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER__NOT_FOUND));

        return orderPresent;
    }

}
