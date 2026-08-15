package com.tochratana.learnSpring.feature.fileUpload;


import com.tochratana.learnSpring.feature.fileUpload.dto.FileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @GetMapping
    public Page<FileResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return fileUploadService.findAll(pageNumber, pageSize);
    }

    @GetMapping("/{name}")
    public FileResponse findByName(@PathVariable String name) {
        return fileUploadService.findByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/multiple")
    public List<FileResponse> uploadMultiple(
            @RequestPart List<MultipartFile> files
    ) {
        return fileUploadService.uploadMultiple(files);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FileResponse upload(@RequestPart MultipartFile file) {
        return  fileUploadService.upload(file);
    }

}
