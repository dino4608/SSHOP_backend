package com.dino.sshop._application.aop;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * define a structure of an apiResponse or apiError
 * @case: apiResponse includes: success (true), code, meta, data
 * @case: apiError includes: success (false), code, error
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiRes<T> {
    boolean success;
    int code;
    String error;
    T result;
}
