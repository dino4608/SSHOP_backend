package com.dino.sshop._application.service.media.impl;

import com.dino.sshop._application.service.media.IMediaAppService;
import com.dino.sshop._application.external.resources.IMediaResourcesService;
import com.dino.sshop._application.external.resources.model.UploadRes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MediaAppServiceImpl implements IMediaAppService {
    IMediaResourcesService mediaResourceService;

    @Override
    public UploadRes upload(MultipartFile file, String folder) {
        return this.mediaResourceService.upload(file, folder);
    }

}
