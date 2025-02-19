package com.dino.sshop._api.rest.identity;

import com.dino.sshop._application.service.identity.IAccessAppService;
import com.dino.sshop._domain.identity.model.request.Oauth2LoginRequest;
import com.dino.sshop._domain.identity.model.request.UsernameLoginRequest;
import com.dino.sshop._domain.identity.model.response.AuthenticationResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

public class AccessController {

    //ADMIN//
    @RestController
    @RequestMapping("/admin/api/v1/access")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class AccessAdminController {

        IAccessAppService accessAppService;

        //LOGIN//
        @PostMapping("/login/username")
        public ResponseEntity<AuthenticationResponse> loginUsername(@RequestBody UsernameLoginRequest request) {
            HttpHeaders headers = new HttpHeaders();
            var result = this.accessAppService.loginUsernameForAdmin(request, headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(result);
        }

        //REFRESH//
        @GetMapping("/refresh")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<AuthenticationResponse> refresh(@CookieValue("REFRESH_TOKEN") String refreshToken) {
            HttpHeaders headers = new HttpHeaders();
            var result = this.accessAppService.refreshForAdmin(refreshToken, headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(result);
        }

        //LOGOUT//
        @GetMapping("/logout")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<Void> logout() {
            HttpHeaders headers = new HttpHeaders();
            this.accessAppService.logoutForAdmin(headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(null);
        }
    }

    //SELLER//
    @RestController
    @RequestMapping("/seller/api/v1/access")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class AccessSellerController {

        IAccessAppService accessAppService;

        //LOGIN//
        @PostMapping("/login/username")
        public ResponseEntity<AuthenticationResponse> loginUsername(@RequestBody UsernameLoginRequest request) {
            HttpHeaders headers = new HttpHeaders();
            var result = this.accessAppService.loginUsernameForSeller(request, headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(result);
        }

        @PostMapping("/login/oauth2")
        public ResponseEntity<AuthenticationResponse> loginOauth2(@RequestParam Oauth2LoginRequest request) {
            HttpHeaders headers = new HttpHeaders();
            var result = this.accessAppService.loginOauth2ForSeller(request, headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(result);
        }

        //REGISTER//
        @PostMapping("/signup/username")
        public ResponseEntity<AuthenticationResponse> registerUsername(@RequestBody UsernameLoginRequest request) {
            HttpHeaders headers = new HttpHeaders();
            var result = this.accessAppService.signupUsernameForSeller(request, headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(result);
        }

        @PostMapping("/signup/oauth2")
        public ResponseEntity<AuthenticationResponse> registerOauth2(@RequestParam Oauth2LoginRequest request) {
            HttpHeaders headers = new HttpHeaders();
            var result = this.accessAppService.signupOauth2ForSeller(request, headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(result);
        }

        //REFRESH//
        @GetMapping("/refresh")
        @PreAuthorize("hasRole('SELLER')")
        public ResponseEntity<AuthenticationResponse> refresh(@CookieValue("REFRESH_TOKEN") String refreshToken) {
            HttpHeaders headers = new HttpHeaders();
            var result = this.accessAppService.refreshForSeller(refreshToken, headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(result);
        }

        //LOGOUT//
        @GetMapping("/logout")
        @PreAuthorize("hasRole('SELLER')")
        public ResponseEntity<Void> logout() {
            HttpHeaders headers = new HttpHeaders();
            this.accessAppService.logoutForSeller(headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(null);
        }
    }

    //BUYER//
    @RestController
    @RequestMapping("/api/v1/access")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class AccessBuyerController {

        IAccessAppService accessAppService;

        //LOGIN//
        @PostMapping("/login/username")
        public ResponseEntity<AuthenticationResponse> loginUsername(@RequestBody UsernameLoginRequest request) {
            HttpHeaders headers = new HttpHeaders();
            var result = this.accessAppService.loginUsernameForBuyer(request, headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(result);
        }

        @PostMapping("/login/oauth2")
        public ResponseEntity<AuthenticationResponse> loginOauth2(@RequestParam Oauth2LoginRequest request) {
            HttpHeaders headers = new HttpHeaders();
            var result = this.accessAppService.loginOauth2ForBuyer(request, headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(result);
        }

        //REGISTER//
        @PostMapping("/signup/username")
        public ResponseEntity<AuthenticationResponse> signupUsername(@RequestBody UsernameLoginRequest request) {
            HttpHeaders headers = new HttpHeaders();
            var result = this.accessAppService.signupUsernameForBuyer(request, headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(result);
        }

        @PostMapping("/signup/oauth2")
        public ResponseEntity<AuthenticationResponse> signupOauth2(@RequestParam Oauth2LoginRequest request) {
            HttpHeaders headers = new HttpHeaders();
            var result = this.accessAppService.signupOauth2ForBuyer(request, headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(result);
        }

        //REFRESH//
        @GetMapping("/refresh")
        @PreAuthorize("hasRole('BUYER')")
        public ResponseEntity<AuthenticationResponse> refresh(@CookieValue("REFRESH_TOKEN") String refreshToken) {
            HttpHeaders headers = new HttpHeaders();
            var result = this.accessAppService.refreshForBuyer(refreshToken, headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(result);
        }

        //LOGOUT//
        @GetMapping("/logout")
        @PreAuthorize("hasRole('BUYER')")
        public ResponseEntity<Void> logout() {
            HttpHeaders headers = new HttpHeaders();
            this.accessAppService.logoutForBuyer(headers);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(null);
        }
    }
}
