package com.tochratana.ecommerce.feature.fileUpload;

import com.tochratana.ecommerce.feature.fileUpload.dto.FileResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {
    Page<FileResponse> findAll(int pageNumber, int pageSize);
    FileResponse findByName(String name);
    List<FileResponse> uploadMultiple(List<MultipartFile> files);
    FileResponse upload(MultipartFile file);
}
