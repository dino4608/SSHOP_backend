package com.dmon.sshop._api.rest.system;

import com.dmon.sshop._application.service.media.IMediaAppService;
import com.dmon.sshop._application.external.resources.model.UploadRes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

public class MediaController {
    @RestController
    @RequestMapping("/admin/api/v1/media")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class MediaAdminController {
        IMediaAppService mediaAppService;

        //UPLOAD//
        @PostMapping("/upload")
        public ResponseEntity<UploadRes> upload(
                @RequestParam(name = "file", required = false) MultipartFile file,
                @RequestParam("folder") String folder
        ) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.mediaAppService.upload(file, folder));
        }

    }

    @RestController
    @RequestMapping("/seller/api/v1/media")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class MediaSellerController {
        IMediaAppService mediaAppService;

        //UPLOAD//
        @PostMapping("/upload")
        public ResponseEntity<UploadRes> upload(
                @RequestParam(name = "file", required = false) MultipartFile file,
                @RequestParam("folder") String folder
        ) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.mediaAppService.upload(file, folder));
        }

    }

    @RestController
    @RequestMapping("/api/v1/media")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class MediaBuyerController {
        IMediaAppService mediaAppService;

        //UPLOAD//
        @PostMapping("/upload")
        public ResponseEntity<UploadRes> upload(
                @RequestParam(name = "file", required = false) MultipartFile file,
                @RequestParam("folder") String folder
        ) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.mediaAppService.upload(file, folder));
        }
    }
}
