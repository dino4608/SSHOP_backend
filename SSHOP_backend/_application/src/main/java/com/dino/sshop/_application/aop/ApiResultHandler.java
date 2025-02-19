package com.dino.sshop._application.aop;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ApiResultHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        //case: body is a string
        if (!MediaType.APPLICATION_JSON.equals(selectedContentType))
            return body;
        //case: throw exception
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int apiCode = servletResponse.getStatus();
        if (apiCode >= 400)
            return body;
        //case: swagger
        String path = request.getURI().getPath();
        if (path.startsWith("/sshop/v3/api-docs") || path.startsWith("/sshop/swagger-ui"))
            return body;
        //case: success
        return ApiRes.builder()
                .success(true)
                .code(1)
                .result(body)
                .build();
    }
}
