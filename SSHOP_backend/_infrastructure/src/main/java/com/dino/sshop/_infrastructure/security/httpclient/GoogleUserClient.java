package com.dino.sshop._infrastructure.security.httpclient;

import com.dino.sshop._infrastructure.security.model.response.GoogleUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "outbound-user-client", url = "https://www.googleapis.com")
public interface GoogleUserClient {
    @GetMapping(value = "/oauth2/v1/userinfo")
    GoogleUserInfoResponse getUserInfo(@RequestParam("alt") String alt,
                                       @RequestParam("access_token") String accessToken);
}
