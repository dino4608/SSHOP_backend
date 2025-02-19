package com.dino.sshop._domain.product.model.request;

import com.dino.sshop._domain.product.model.entity.Product;
import com.dino.sshop._domain.inventory.model.entity.Sku;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

public class ProductReq {
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Create {
        ProductReq.CategoryReq category;

        @Valid
        @NotEmpty(message = "PRODUCT__SKUS_NOT_EMPTY")
        List<ProductReq.SkuReq> skus;

        @NotEmpty(message = "PRODUCT__NAME_NOT_EMPTY")
        String name;

        String description;

        @NotEmpty(message = "PRODUCT__THUMB_NOT_EMPTY")
        String thumb;

        List<String> photos;

        String video;

        String sizeChart;

        @NotNull(message = "PRODUCT__WEIGHT_NOT_EMPTY")
        @Min(message = "PRODUCT__WEIGHT_MIN", value = 50)
        Float weight;

        @NotEmpty(message = "PRODUCT__LOCATION_NOT_EMPTY")
        String location;

        ArrayList<Product.Attribute> attributes;

        ArrayList<Product.TierVariation> tierVariations;

    }

    //THE NESTED CLASSES//
    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CategoryReq {
        String id;
    }

    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SkuReq {
        @Valid
        @NotNull(message = "SKU__INVENTORY_NOT_EMPTY")
        ProductReq.InventoryReq inventory;

        @NotEmpty(message = "SKU__NO_NOT_EMPTY")
        String skuCode;

        String tierName;

        Integer[] tierIndex;

        @NotNull(message = "SKU__PRODUCT_COST_NOT_EMPTY")
        @Min(message = "SKU__PRODUCT_COST_MIN", value = 1000)
        Float productCost;

        @NotNull (message = "SKU__RETAIL_PRICE_NOT_EMPTY")
        @Min(message = "SKU_RETAIL_PRICE_MIN", value = 1000)
        Float retailPrice;

        private ArrayList<Sku.Specification> specifications;
    }

    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class InventoryReq {
        @NotNull(message = "INVENTORY__STOCKS_NOT_EMPTY")
        @Min(message = "INVENTORY__STOCKS_MIN", value = 1)
        Integer stocks;
    }
}
