package com.dino.sshop._infrastructure.security.httpclient;

import com.dino.sshop._infrastructure.security.model.request.ExchangeTokenRequest;
import com.dino.sshop._infrastructure.security.model.response.ExchangeTokenResponse;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "oauth2-google-client", url = "https://oauth2.googleapis.com")
public interface GoogleTokenClient {
    @PostMapping(value = "/token", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ExchangeTokenResponse exchangeToken(@QueryMap ExchangeTokenRequest request);
}
