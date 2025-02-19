package com.dino.sshop._domain.product.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

public class CategoryReq {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Create {
        @NotEmpty(message = "CATEGORY__NAME_NOT_EMPTY")
        @Size(message = "CATEGORY__NAME_OUT_SIZE", min = 4, max = 40)
        String name;

        @Size(message = "CATEGORY__DESCRIPTION_OUT_SIZE", max = 255)
        String description;

        @Size(message = "CATEGORY__PHOTO_OUT_SIZE", max = 255)
        String photo;

        @Min(message = "CATEGORY__POSITION_MIN", value = 1)
        Integer position;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Update {
        @Size(message = "CATEGORY__NAME_OUT_SIZE", min = 2, max = 40)
        String name;

        @Size(message = "CATEGORY__DESCRIPTION_OUT_SIZE", max = 255)
        String description;

        @Size(message = "CATEGORY__PHOTO_OUT_SIZE", max = 255)
        String photo;

        @Min(message = "CATEGORY__POSITION_MIN", value = 1)
        Integer position;
    }
}
