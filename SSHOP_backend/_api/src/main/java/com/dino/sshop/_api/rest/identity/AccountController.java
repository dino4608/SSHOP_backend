package com.dino.sshop._api.rest.identity;

import com.dino.sshop._application.service.identity.IAccountAppService;
import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import com.dino.sshop._domain.identity.model.entity.Account;
import com.dino.sshop._domain.identity.model.entity.Shop;
import com.dino.sshop._domain.identity.model.request.AccountSettleRequest;
import com.dino.sshop._domain.identity.model.request.ShopSettleRequest;
import com.dino.sshop._domain.identity.model.response.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AccountController {

    //ADMIN//
    @RestController
    @RequestMapping("/admin/api/v1/account")
    @PreAuthorize("hasRole('ADMIN')")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class AccountAdminController {

        IAccountAppService accountAppService;

        //INFO//
        @GetMapping("/info/get")
        public ResponseEntity<AccountInfoResponse> getInfo() {
            return ResponseEntity.ok()
                    .body(this.accountAppService.getAccountInfo());
        }

        //READ//
        @GetMapping("/list")
        public ResponseEntity<List<Account>> listAll() {
            return ResponseEntity.ok()
                    .body(this.accountAppService.listAll());
        }

        @GetMapping("/find/{accountId}")
        public ResponseEntity<Account> getOne(@PathVariable("accountId") String accountId) {
            return ResponseEntity.ok()
                    .body(this.accountAppService.getOne(accountId));
        }
    }

    //SELLER//
    @RestController
    @RequestMapping("/seller/api/v1/account")
    @PreAuthorize("hasRole('SELLER')")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class AccountSellerController {

        IAccountAppService accountAppService;

        //INFO//
        @GetMapping("/info/get")
        public ResponseEntity<ShopInfoResponse> getInfo() {
            return ResponseEntity.ok()
                    .body(this.accountAppService.getShopInfo());
        }

        @GetMapping("/login/get")
        public ResponseEntity<LoginInfoResponse> getLogin() {
            return ResponseEntity.ok()
                    .body(this.accountAppService.getLoginInfo());
        }

        @GetMapping("/contact/get")
        public ResponseEntity<ContactInfoResponse> getContact() {
            return ResponseEntity.ok()
                    .body(this.accountAppService.getContactInfo());
        }

        @GetMapping("/citizen/get")
        public ResponseEntity<CitizenInfoResponse> getCitizen() {
            return ResponseEntity.ok()
                    .body(this.accountAppService.getCitizenInfo());
        }

        //SETTLE//
        @PostMapping("/info/settle")
        public ResponseEntity<Shop> settleInfo(@RequestBody ShopSettleRequest request) {
            return ResponseEntity.ok()
                    .body(this.accountAppService.settleShopInfo(request));
        }

    }

    //BUYER//
    @RestController
    @RequestMapping("/api/v1/account")
    @PreAuthorize("hasRole('BUYER')")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class AccountBuyerController {

        IAccountAppService accountAppService;

        //INFO//
        @GetMapping("/info/get")
        public ResponseEntity<AccountInfoResponse> getInfo() {
            return ResponseEntity.ok()
                    .body(this.accountAppService.getAccountInfo());
        }

        @GetMapping("/login/get")
        public ResponseEntity<LoginInfoResponse> getLogin() {
            return ResponseEntity.ok()
                    .body(this.accountAppService.getLoginInfo());
        }

        //SETTLE//
        @PostMapping("/info/settle")
        public ResponseEntity<Account> settleInfo(@RequestBody AccountSettleRequest request) {
            return ResponseEntity.ok()
                    .body(this.accountAppService.settleAccountInfo(request));
        }

        @PostMapping("/info/update")
        public ResponseEntity<Void> updateInfo(Object request) {
            throw new AppException(ErrorCode.SYSTEM__DEVELOPING_FEATURE);
        }

        @PostMapping("/username/update")
        public ResponseEntity<Void> updateUsername(Object request) {
            throw new AppException(ErrorCode.SYSTEM__DEVELOPING_FEATURE);
        }

        @PostMapping("/password/update")
        public ResponseEntity<Void> updatePassword(Object request) {
            throw new AppException(ErrorCode.SYSTEM__DEVELOPING_FEATURE);
        }
    }


}
