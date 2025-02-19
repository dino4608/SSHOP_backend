package com.dino.sshop._application.aop;

import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class ApiErrorHandler {
    /**
     * Handle app exception threw in the service tier
     *
     * @param: AppException
     * @return: ApiResponse
     */
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiRes<Object>> handleException(AppException exception) {
        ApiRes<Object> apiError = ApiRes.builder()
                .success(false)
                .code(exception.getCode())
                .error(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiError);
    }

    /**
     * Handle app exception unhandled
     *
     * @param: RuntimeException
     * @return: ApiResponse
     */
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiRes<Object>> handleException(RuntimeException exception) {
        log.error("Unhandled exception occurred: ", exception);

        ErrorCode error = ErrorCode.SYSTEM__UNHANDLED_EXCEPTION;
        return ResponseEntity
                .status(error.getStatus())
                .body(ApiRes.builder()
                        .success(false)
                        .code(error.getCode())
                        .error(error.getMessage())
                        .build()
                );
    }

    /**
     * Handle exception threw in the validation tier
     *
     * @params: RuntimeException
     * @return: ApiResponse
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRes<Object>> handleException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode error = ErrorCode.SYSTEM__KEY_UNSUPPORTED;
        try {
            error = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ignored) {
        }

        ApiRes<Object> apiError = ApiRes.builder()
                .success(false)
                .code(error.getCode())
                .error(error.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiError);
    }


    /**
     * Handle exception threw in the service tier by spring security
     *
     * @return ApiResponse
     * @param: AccessDeniedException
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiRes<Object>> handleException(AccessDeniedException exception) {
        ErrorCode error = ErrorCode.SECURITY__UNAUTHORIZED;
        return ResponseEntity
                .status(error.getStatus())
                .body(ApiRes.builder()
                        .success(false)
                        .code(error.getCode())
                        .error(error.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    ResponseEntity<ApiRes<Object>> handleException(HttpRequestMethodNotSupportedException exception) {
        ErrorCode error = ErrorCode.SYSTEM__METHOD_NOT_SUPPORTED;
        return ResponseEntity
                .status(error.getStatus())
                .body(ApiRes.builder()
                        .success(false)
                        .code(error.getCode())
                        .error(String.format(error.getMessage(), exception.getMethod()))
                        .build()
                );
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    ResponseEntity<ApiRes<Object>> handleException(NoResourceFoundException exception) {
        ErrorCode error = ErrorCode.SYSTEM__ROUTE_NOT_SUPPORTED;
        return ResponseEntity
                .status(error.getStatus())
                .body(ApiRes.builder()
                        .success(false)
                        .code(error.getCode())
                        .error(String.format(error.getMessage(), exception.getResourcePath()))
                        .build()
                );
    }
}
