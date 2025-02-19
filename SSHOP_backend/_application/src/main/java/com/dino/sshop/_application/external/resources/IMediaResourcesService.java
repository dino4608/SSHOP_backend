package com.dino.sshop._application.external.resources;

import com.dino.sshop._application.external.resources.model.UploadRes;
import org.springframework.web.multipart.MultipartFile;

public interface IMediaResourcesService {
    //UPLOAD//
    UploadRes upload(MultipartFile file, String folder);
}
