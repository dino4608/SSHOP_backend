package com.dmon.sshop._api.rest.product;

import com.dmon.sshop._application.service.product.ICategoryAppService;
import com.dmon.sshop._domain.common.base.PageReq;
import com.dmon.sshop._domain.common.base.PageRes;
import com.dmon.sshop._domain.common.util.AppUtil;
import com.dmon.sshop._domain.product.model.projection.CategoryProj;
import com.dmon.sshop._domain.product.model.request.CategoryReq;
import com.dmon.sshop._domain.product.model.entity.Category;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    //ADMIN//
    @RestController
    @RequestMapping("/admin/api/v1/category")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class CategoryAdminController {
        ICategoryAppService categoryAppService;

        //CREATE//
        @PostMapping("/create")
        public ResponseEntity<Category> create(
                @RequestBody @Valid CategoryReq.Create cateDto)
        {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(categoryAppService.create(cateDto));
        }

        //UPDATE//
        @PatchMapping("/update/{cateId}")
        public ResponseEntity<Category> update(
                @RequestBody @Valid CategoryReq.Update cateDto,
                @PathVariable("cateId") String cateId)
        {
            return  ResponseEntity
                    .ok()
                    .body(this.categoryAppService.update(cateDto, cateId));
        }

        //LIST//
        @GetMapping("/list")
        public ResponseEntity<PageRes<CategoryProj>> findAll(
                @ModelAttribute PageReq pageReq,
                @RequestParam(value = "sort", defaultValue = "position") String sort,
                @RequestParam(value = "direct", defaultValue = "asc") String direct
        ) {
            pageReq.setSort(sort);
            pageReq.setDirect(direct);
            return ResponseEntity
                    .ok()
                    .body(this.categoryAppService.findAll(AppUtil.toPageable(pageReq)));
        }

        //FIND//
        @GetMapping("/find/{cateId}")
        public ResponseEntity<Category> find(
                @PathVariable("cateId") String cateId
        ) {
            return ResponseEntity
                    .ok()
                    .body(this.categoryAppService.find(cateId));
        }

        //DELETE//
        @DeleteMapping("/delete/{cateId}")
        public ResponseEntity<Void> delete(
                @PathVariable("cateId") String cateId
        ) {
            return ResponseEntity
                    .ok()
                    .body(this.categoryAppService.delete(cateId));
        }

    }

    //SELLER//
    @RestController
    @RequestMapping("/seller/api/v1/category")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class CategorySellerController {
        ICategoryAppService categoryAppService;

        //LIST//
        @GetMapping("/tree")
        public ResponseEntity<List<CategoryProj>> findTree() {
            return ResponseEntity
                    .ok()
                    .body(this.categoryAppService.findTree());
        }
    }

    //BUYER//
    @RestController
    @RequestMapping("/api/v1/category")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class CategoryBuyerController {
        ICategoryAppService categoryAppService;

        //LIST//
        @GetMapping("/tree")
        public ResponseEntity<List<CategoryProj>> findTree() {
            return ResponseEntity
                    .ok()
                    .body(this.categoryAppService.findTree());
        }
    }
}
