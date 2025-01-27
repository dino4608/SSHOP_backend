package com.dmon.sshop._domain.shopping.factory;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.common.util.AppUtil;
import com.dmon.sshop._domain.inventory.model.entity.Inventory;
import com.dmon.sshop._domain.shopping.model.entity.Order;
import com.dmon.sshop._domain.shopping.model.entity.OrderItem;

import java.time.Instant;
import java.util.ArrayList;

public class OrderFactory {
    /**
     * if statusType is DRAFT:
     * - status is DRAFT
     * - address id is null
     * - items should check quantity, cascade, locked price
     * - count is sum of items
     * - subtotal is sum of promo price
     * - shipping fee is default 46k
     * - discount is temporarily 0k
     * - shipping discount is default 46k
     * - total = subtotal + shipping fee - discount - shipping discount
     */
    public static Order createOrder(Order order, Order.StatusType statusType) {

        switch (statusType) {
            case DRAFT -> {
                return createOrderAsDraft(order);
            }
            case UNPAID -> {
                return createOrderAsUnpaid(order);
            }
            case PREPARING, TRANSIT, DELIVERING, DELIVERED, CANCELED, RETURN ->
                throw new AppException(ErrorCode.SYSTEM__DEVELOPING_FEATURE);
        }

        throw new AppException(ErrorCode.SYSTEM__KEY_UNSUPPORTED);
    }

    private static Order createOrderAsUnpaid(Order order) {
        if (order.getPaymentMethod().equals(Order.PaymentMethodType.COD.name()))
            order.setStatus(Order.StatusType.PREPARING.name());

        order.setStatus(Order.StatusType.UNPAID.name());
        order.setOrderDate(Instant.now());
        return order;
    }

    private static Order createOrderAsDraft(Order order) {
        order.setStatus(Order.StatusType.DRAFT.name());
        order.setItems(createItems(order));
        order.setCount(order.getItems().size());
        order.setSubtotal(createSubtotal(order));
        order.setShippingFee(createShippingFee());
        order.setDiscount(createDiscount());
        order.setShippingDiscount(createShippingDiscount());
        order.setTotal(createTotal(order));
        return order;
    }

    private static ArrayList<OrderItem> createItems(Order order) {
        ArrayList<OrderItem> items = order.getItems();

        items.stream().parallel().forEach((item) -> {
            Inventory inventory = item.getSku().getInventory();
            item.setQuantity(createQuantity(item.getQuantity(), inventory.getStocks()));

            item.setLockedPrice(item.getSku().getRetailPrice());

            inventory.setStocks(inventory.getStocks() - item.getQuantity());
            inventory.setSales(inventory.getSales() + item.getQuantity());
            inventory.setSku(item.getSku());
            item.getSku().setInventory(inventory);

            item.getSku().setOrderItems(items);

            item.setOrder(order);
        });

        return items;
    }

    private static int createQuantity(int quantity, Integer stocks) {
        if (quantity < 1)
            throw new AppException(ErrorCode.ORDER__QUANTITY_MIN);

        if (quantity > stocks)
            throw new AppException(ErrorCode.ORDER__QUANTITY_MAX);

        return quantity;
    }

    private static float createSubtotal(Order order) {
        if (AppUtil.isEmpty(order.getSubtotal()))
            order.setSubtotal(0); // todo: debug

        order.getItems().stream().parallel().forEach((item) -> order.setSubtotal(
                order.getSubtotal() + item.getLockedPrice() * item.getQuantity()));

        return order.getSubtotal();
    }

    private static float createShippingFee() {
        return 46;
    }

    private static float createDiscount() {
        return 0;
    }

    private static float createShippingDiscount() {
        return 46;
    }

    private static float createTotal(Order order) {
        order.setTotal(
                order.getSubtotal() + order.getShippingFee() -
                        (order.getDiscount() + order.getShippingDiscount()));

        return order.getTotal();
    }

}
