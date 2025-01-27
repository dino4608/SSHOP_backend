package com.dmon.sshop._domain.common.util;

import com.dmon.sshop._domain.common.base.PageReq;
import com.dmon.sshop._domain.common.base.PageRes;
import com.github.slugify.Slugify;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class AppUtil {
    /**
     * @desc check a value object is present
     * @param value: Object
     * @return true if non-null, false if null
     */
    public static boolean isPresent(Object value) {
        return value != null;
    }

    /**
     * @desc check a value object is empty
     * @param value: Object
     * @return true if null, false if non-null
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
        Pageable pageable = AppUtil.toPageable(pageReq.getPage(), pageReq.getSize(), pageReq.getSort(),
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
