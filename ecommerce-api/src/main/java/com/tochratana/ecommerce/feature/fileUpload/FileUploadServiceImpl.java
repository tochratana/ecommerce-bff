package com.tochratana.ecommerce.feature.fileUpload;

import com.tochratana.ecommerce.feature.fileUpload.dto.FileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final FileUploadRepository fileUploadRepository;
    private final FileUploadMapper fileUploadMapper;

    @Value("${file-upload.server-path}")
    private String serverPath;


    @Override
    public Page<FileResponse> findAll(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortById);

        return fileUploadRepository.findAll(pageable)
                .map(fileUploadMapper::toResponse);
    }

    @Override
    public FileResponse findByName(String name) {
        return fileUploadRepository.findByName(name)
                .map(fileUploadMapper::toResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "File has not been found"
                ));
    }

    @Override
    public List<FileResponse> uploadMultiple(List<MultipartFile> files) {
        return files.stream()
                .map(this::saveFile)
                .collect(Collectors.toList());
    }


    @Override
    public FileResponse upload(MultipartFile file) {
        return saveFile(file);
    }


    private FileResponse saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File must not be empty");
        }

        String fileName = UUID.randomUUID().toString();
        String fileExt = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if (fileExt == null || fileExt.isBlank() || fileExt.length() > 6) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "File must have an extension of at most 6 characters"
            );
        }

        Path uploadDirectory = Paths.get(serverPath).toAbsolutePath().normalize();
        Path path = uploadDirectory.resolve(fileName + "." + fileExt).normalize();
        try {
            Files.createDirectories(uploadDirectory);
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to save the uploaded file",
                    e
            );
        }

        FileUpload fileUpload = new FileUpload();
        fileUpload.setName(fileName);
        fileUpload.setExtension(fileExt);
        fileUpload.setMediaType(file.getContentType() == null
                ? "application/octet-stream"
                : file.getContentType());
        fileUpload.setSize(file.getSize());

        fileUploadRepository.save(fileUpload);

        return fileUploadMapper.toResponse(fileUpload);
    }
}
