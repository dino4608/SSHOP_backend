package com.dino.sshop._domain.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class AppException extends RuntimeException {
    public AppException(ErrorCode error)  {
        super(error.getMessage());
        this.code = error.getCode();
        this.message = error.getMessage();
        this.status = error.getStatus();
    }

    private int code;
    private String message;
    private HttpStatusCode status;
}
