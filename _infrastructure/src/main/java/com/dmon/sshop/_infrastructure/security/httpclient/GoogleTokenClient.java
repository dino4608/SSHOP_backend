package com.dmon.sshop._infrastructure.security.httpclient;

import com.dmon.sshop._infrastructure.security.model.request.ExchangeTokenReq;
import com.dmon.sshop._infrastructure.security.model.response.ExchangeTokenRes;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "oauth2-google-client", url = "https://oauth2.googleapis.com")
public interface GoogleTokenClient {
    @PostMapping(value = "/token", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ExchangeTokenRes exchangeToken(@QueryMap ExchangeTokenReq exchangeTokenRequest);
}
