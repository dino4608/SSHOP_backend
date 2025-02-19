package com.dino.sshop._application.service.media;

import com.dino.sshop._application.external.resources.model.UploadRes;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IMediaAppService {
    //UPLOAD//
    UploadRes upload(MultipartFile file, String folder);
}
