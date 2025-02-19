package com.dino.sshop._domain.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    //SUCCESS CODE: 1//
    //SYSTEM 1000+//
    SYSTEM__UNHANDLED_EXCEPTION(1000, "An unhandled error.", HttpStatus.INTERNAL_SERVER_ERROR),
    SYSTEM__DEVELOPING_FEATURE(1001, "The feature is still developing.", HttpStatus.INTERNAL_SERVER_ERROR),
    SYSTEM__UNIMPLEMENTED_FEATURE(1002, "The feature is still developing.", HttpStatus.INTERNAL_SERVER_ERROR),
    SYSTEM__KEY_UNSUPPORTED(1003, "The key is unsupported.", HttpStatus.INTERNAL_SERVER_ERROR),
    SYSTEM__METHOD_NOT_SUPPORTED(1004, "Method '%s' is not supported.", HttpStatus.INTERNAL_SERVER_ERROR),
    SYSTEM__ROUTE_NOT_SUPPORTED(1005, "Route '%s' not supported.", HttpStatus.INTERNAL_SERVER_ERROR),
    //ACCOUNT 1100+//
    ACCOUNT__USERNAME_MIN(1100, "Username should be at least 4 characters.", HttpStatus.BAD_REQUEST),
    ACCOUNT__PASSWORD_MIN(1101, "Password should be at least 6 characters.", HttpStatus.BAD_REQUEST),
    ACCOUNT__EMAIL_NOT_MATCHED(1102, "Email should follow the email format.", HttpStatus.BAD_REQUEST),
    ACCOUNT__PHONE_NOT_MATCHED(1103, "Phone should follow the phone format.", HttpStatus.BAD_REQUEST),
    ACCOUNT__NOT_FOUND(1104, "Account is not found.", HttpStatus.BAD_REQUEST),
    ACCOUNT__EXISTED(1105, "Account has already existed.", HttpStatus.BAD_REQUEST),
    ACCOUNT__EXISTED_BUYER_SELLER(1106, "Account has already existed as buyer or seller.", HttpStatus.BAD_REQUEST),
    ACCOUNT__LACK_INFO(1107, "Account is lack of required information.", HttpStatus.INTERNAL_SERVER_ERROR),
    ACCOUNT__NOT_HASH_PASSWORD(1108, "Password hasn't hashed yet.", HttpStatus.INTERNAL_SERVER_ERROR),
    //SECURITY 1200+//
    SECURITY__UNAUTHENTICATED(1200, "You are not allowed to access this resource.", HttpStatus.UNAUTHORIZED),
    SECURITY__UNAUTHORIZED(1201, "You are not permitted to access this resource.", HttpStatus.FORBIDDEN),
    SECURITY__WRONG_PASSWORD(1202, "Username or password doesn't match any our records. Please try again.", HttpStatus.BAD_REQUEST),
    SECURITY__USER_NOT_REGISTER_SELLER(1203, "The account is a user. Please sign up with user account.", HttpStatus.BAD_REQUEST),
    SECURITY__SELLER_NOT_REGISTER_SELLER(1204, "The account already exists. Please choose a different one.", HttpStatus.BAD_REQUEST),
    SECURITY__ADMIN_NOT_REGISTER_SELLER(1215, "The account is banned from registration as seller.", HttpStatus.BAD_REQUEST),
    SECURITY__NOT_IN_SECURITY(1216, "Authenticating your account is fail. Please login again.", HttpStatus.BAD_REQUEST),
    SECURITY__REFRESH_TOKEN_FAILED(1217, "Refresh token is old or not authentic", HttpStatus.BAD_REQUEST),
    OAUTH2__EXCHANGE_TOKEN_FAILED(1218, "Exchanging code for token failed", HttpStatus.BAD_REQUEST),
    OAUTH2__GET_USERINFO_FAILED(1219, "Getting user information failed", HttpStatus.BAD_REQUEST),
    SECURITY__FORBIDDEN(1220, "Action is forbidden.", HttpStatus.FORBIDDEN),
    //TOKEN 1300+//
    TOKEN__NOT_FOUND(1300, "Token is not found", HttpStatus.BAD_REQUEST),
    //SHOP 1400+//
    SHOP__NOT_FOUND(1401, "Shop is not found.", HttpStatus.BAD_REQUEST),
    SHOP__LACK_INFO(1402, "Shop is lack of required information.", HttpStatus.INTERNAL_SERVER_ERROR),
    //CATEGORY 1500+//
    CATEGORY__NAME_OUT_SIZE(1500, "Name should be between 4 and 40 characters.", HttpStatus.BAD_REQUEST),
    CATEGORY__DESCRIPTION_OUT_SIZE(1501, "Description should be at most 255 characters.", HttpStatus.BAD_REQUEST),
    CATEGORY__PHOTO_OUT_SIZE(1502, "Photo should be at most 255 characters.", HttpStatus.BAD_REQUEST),
    CATEGORY__NAME_NOT_EMPTY(1503, "Name should not be empty.", HttpStatus.BAD_REQUEST),
    CATEGORY__NAME_UNIQUE(1504, "Name already exists. Please choose a different one.", HttpStatus.BAD_REQUEST),
    CATEGORY__NOT_FOUND(1505, "Category is not found.", HttpStatus.BAD_REQUEST),
    CATEGORY__POSITION_MIN(1506, "Position should be higher or equal to 1.", HttpStatus.BAD_REQUEST),
    //PRODUCT 1600+//
    PRODUCT__SKUS_NOT_EMPTY(1600, "The SKU list should not be empty.", HttpStatus.BAD_REQUEST),
    PRODUCT__NAME_NOT_EMPTY(1601, "The name should not be empty.", HttpStatus.BAD_REQUEST),
    PRODUCT__THUMB_NOT_EMPTY(1602, "The thumb should not be empty.", HttpStatus.BAD_REQUEST),
    PRODUCT__WEIGHT_NOT_EMPTY(1605, "The weight should not be empty.", HttpStatus.BAD_REQUEST),
    PRODUCT__WEIGHT_MIN(1606, "The weight should at least 50 g.", HttpStatus.BAD_REQUEST),
    PRODUCT__LOCATION_NOT_EMPTY(1607, "The location should not be empty.", HttpStatus.BAD_REQUEST),
    //MEDIA 1700+//
    MEDIA__FILE_OUT_EXTENSIONS(1701, "File extensions should be pdf, jpg, jpeg, png, doc or docx.", HttpStatus.BAD_REQUEST),
    MEDIA__FILE_CREATE_DIRECTORY(1702, "An error occurred while creating a media directory.", HttpStatus.INTERNAL_SERVER_ERROR),
    MEDIA__FILE_CREATE_FOLDER(1703, "An error occurred while creating a media folder.", HttpStatus.INTERNAL_SERVER_ERROR),
    MEDIA__FILE_CREATE_PATH(1704, "An error occurred while creating a file path.", HttpStatus.INTERNAL_SERVER_ERROR),
    MEDIA__FILE_STREAM(1705, "An error occurred while stream the file.", HttpStatus.INTERNAL_SERVER_ERROR),
    MEDIA__FILE_EMPTY(1706, "File is empty. Please upload a file.", HttpStatus.BAD_REQUEST),
    //SKU 1800+//
    SKU__INVENTORY_NOT_EMPTY(1800, "The inventory should not be empty.", HttpStatus.BAD_REQUEST),
    SKU__NO_NOT_EMPTY(1801, "The no should not be empty.", HttpStatus.BAD_REQUEST),
    SKU__PRODUCT_COST_NOT_EMPTY(1802, "The product cost should not be empty.", HttpStatus.BAD_REQUEST),
    SKU__PRODUCT_COST_MIN(1803, "The product cost should be at least 1000 VND.", HttpStatus.BAD_REQUEST),
    SKU__RETAIL_PRICE_NOT_EMPTY(1804, "The retail price should not be empty.", HttpStatus.BAD_REQUEST),
    SKU__RETAIL_PRICE_MIN(1805, "The retail price should be at least 1000 VND.", HttpStatus.BAD_REQUEST),
    SKU__NOT_FOUND(1505, "The SKU is not found.", HttpStatus.BAD_REQUEST),
    SKU__CODE_UNIQUE(1506, "The SKU code already exists.", HttpStatus.BAD_REQUEST),
    //INVENTORY 1900+//
    INVENTORY__STOCKS_NOT_EMPTY(1900, "The stocks should not be empty.", HttpStatus.BAD_REQUEST),
    INVENTORY__STOCKS_MIN(1901, "The stocks should be at least 1.", HttpStatus.BAD_REQUEST),
    INVENTORY__SKU_NOT_FOUND(1902, "The sku is not found.", HttpStatus.BAD_REQUEST),
    //CART 2000+//
    CART__NOT_FOUND(2000, "The cart is not found.", HttpStatus.BAD_REQUEST),
    CART__QUANTITY_MIN(2001, "The quantity should be at least 1.", HttpStatus.BAD_REQUEST),
    CART__COUNT_MAX(2002, "The count should be at most 0.", HttpStatus.BAD_REQUEST),
    CART__ITEM_NOT_FOUND(2003, "The cart item is not found.", HttpStatus.BAD_REQUEST),
    //ADDRESS 2100+//
    ADDRESS__NOT_FOUND(2100, "The address is not found.", HttpStatus.BAD_REQUEST),
    //ORDER 2200+//
    ORDER__QUANTITY_MIN(2200, "The quantity should be at least 1.", HttpStatus.BAD_REQUEST),
    ORDER__QUANTITY_MAX(2201, "The quantity should not go beyond the available stock.", HttpStatus.BAD_REQUEST),
    ORDER__NOT_FOUND(2202, "The order is not found.", HttpStatus.BAD_REQUEST),
    ORDER__PAYMENT_METHOD_UNSUPPORTED(2203, "The payment method is unsupported.", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatusCode status;
}
