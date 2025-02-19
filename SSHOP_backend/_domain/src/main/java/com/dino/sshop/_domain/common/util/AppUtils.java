package com.dino.sshop._domain.common.util;

import com.dino.sshop._domain.common.base.PageReq;
import com.dino.sshop._domain.common.base.PageRes;
import com.github.slugify.Slugify;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class AppUtils {

    public static String maskMiddle(String plainText, int keptLength) {
        if (StringUtils.isEmpty(plainText))
            return plainText;
        //the non-masked text
        int maskEndIndex = plainText.length() - keptLength;
        String startText = plainText.substring(0, keptLength);
        String endText = plainText.substring(maskEndIndex);
        //the masked text
        String middleText = new String(new char[maskEndIndex - keptLength]).replace('\0', '*');
        return startText + middleText + endText;
    }

    public static String maskStart(String plainText, int keptLength) {
        if (StringUtils.isEmpty(plainText))
            return plainText;
        //the non-masked text
        int maskEndIndex = plainText.length() - keptLength;
        String endText = plainText.substring(maskEndIndex);
        //the masked text
        String startText = new String(new char[endText.length()]).replace('\0', '*');
        return startText + endText;
    }

    /**
     * @param value: Object
     * @return true if non-null, false if null
     * @desc check a value object is present
     */
    public static boolean isPresent(Object value) {
        return value != null;
    }

    /**
     * @param value: Object
     * @return true if null, false if non-null
     * @desc check a value object is empty
     */
    public static boolean isEmpty(Object value) {
        return value == null;
    }

    public static String genUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String toSlug(String name) {
        Slugify slugify = Slugify.builder().build();
        return slugify.slugify(name);
    }

    /**
     * Map PageResponse from Page of Jpa
     *
     * @param pageJpa: Page of Jpa
     * @return PageResponse
     */
    public static <T> PageRes<T> toPageRes(Page<T> pageJpa) {
        return PageRes.<T>builder()
                .totalPages(pageJpa.getTotalPages())
                .totalElements(pageJpa.getTotalElements())
                .page(pageJpa.getNumber() + 1) // Page of client starts 1. But PageNumber of Jpa starts from 0
                .size(pageJpa.getSize())
                .content(pageJpa.getContent())
                .build();
    }

    /**
     * Build Pageable from page, sort and direct
     *
     * @param page:   int
     * @param size:   int
     * @param sort:   sort on attribute
     * @param direct: direct on asc or desc
     * @return Pageable
     */
    public static Pageable toPageable(int page, int size, String sort, String direct) {
        Sort.Direction direction = direct.equalsIgnoreCase("ASC")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        return PageRequest.of(--page, size, direction, sort); // Page of client starts 1. But PageNumber of Jpa starts
        // from 0
    }

    public static Pageable toPageable(PageReq pageReq) {
        Pageable pageable = AppUtils.toPageable(pageReq.getPage(), pageReq.getSize(), pageReq.getSort(),
                pageReq.getDirect());
        return pageable;
    }

    /**
     * Map source to target with ignoring null value to update partially.
     *
     * @param target Entity will be updated
     * @param source Entity is requested
     * @throws IllegalAccessException when can not access any fields
     */
    public static <T> void updateNonNull(T target, T source) {
        // Get fields (attributes) of source or target
        Field[] fields = source.getClass().getDeclaredFields();

        // Iterate through fields
        Arrays.stream(fields).parallel()
                .peek(field -> field.setAccessible(true)) // Allow to access the private fields
                .filter(field -> {
                    try {
                        return field.get(source) != null; // Select non-null fields
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(field -> {
                    try {
                        field.set(target, field.get(source)); // Map source to target
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
