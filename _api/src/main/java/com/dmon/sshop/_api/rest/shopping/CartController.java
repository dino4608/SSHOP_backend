package com.dmon.sshop._api.rest.shopping;

import com.dmon.sshop._application.service.shopping.ICartAppService;
import com.dmon.sshop._domain.shopping.model.request.CartItemAddReq;
import com.dmon.sshop._domain.shopping.model.request.CartItemEditReq;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class CartController {

    @RestController
    @RequestMapping("admin/cart")
    @AllArgsConstructor
    public static class CartAdminController {

    }

    @RestController
    @RequestMapping("admin/cart")
    @AllArgsConstructor
    public static class CartSellerController {

    }

    @RestController
    @RequestMapping("/cart")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class CartBuyerController {

        ICartAppService cartAppService;

        //CREATE//
        @PostMapping("/add_to_cart")
        public ResponseEntity<Object> addToCart(CartItemAddReq cartItemAddReq) {
            return ResponseEntity
                    .ok()
                    .body(this.cartAppService.addToCart(cartItemAddReq));
        }

        //READ//
        @GetMapping("/get")
        public ResponseEntity<Object> get() {
            return ResponseEntity
                    .ok()
                    .body(this.cartAppService.get());
        }

        //UPDATE//
        @PatchMapping("/edit_quantity")
        public ResponseEntity<Object> editQuantity(CartItemEditReq cartItemEditReq) {
            return ResponseEntity
                    .ok()
                    .body(this.cartAppService.editQuantity(cartItemEditReq));
        }

        //DELETE//
        @DeleteMapping("/remove_from_cart/{ids}")
        public ResponseEntity<Object> removeFromCart(@PathVariable("ids") String cartItemsIds) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(this.cartAppService.removeFromCart(cartItemsIds));
        }

    }
}
