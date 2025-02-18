package com.dmon.sshop._api.rest.shopping;

import com.dmon.sshop._application.service.shopping.IOrderAppService;
import com.dmon.sshop._domain.shopping.model.request.OrderCheckoutReq;
import com.dmon.sshop._domain.shopping.model.request.OrderPlaceReq;
import com.dmon.sshop._domain.shopping.model.response.OrderCheckoutRes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class OrderController {
    
    @RestController
    @RequestMapping("admin/order")
    @AllArgsConstructor
    public static class OrderAdminController {

    }

    @RestController
    @RequestMapping("admin/order")
    @AllArgsConstructor
    public static class OrderSellerController {

    }

    @RestController
    @RequestMapping("/order")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class OrderBuyerController {

        IOrderAppService orderAppService;

        @PostMapping("/checkout")
        public ResponseEntity<OrderCheckoutRes> checkout(OrderCheckoutReq orderCheckoutReq) {
            return ResponseEntity
                    .ok()
                    .body(this.orderAppService.checkout(orderCheckoutReq));
        }

        @PatchMapping("/place")
        public ResponseEntity<Void> place(OrderPlaceReq orderPlaceReq) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(this.orderAppService.place(orderPlaceReq));
        }

    }
}
