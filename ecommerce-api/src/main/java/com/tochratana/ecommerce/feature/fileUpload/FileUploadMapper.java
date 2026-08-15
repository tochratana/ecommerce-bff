package com.tochratana.ecommerce.feature.fileUpload;

import com.tochratana.ecommerce.feature.fileUpload.dto.FileResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUploadMapper {

    @Value("${file-upload.base-uri}")
    private String baseUri;

    public FileResponse toResponse(FileUpload fileUpload) {
        return FileResponse.builder()
                .name(fileUpload.getName())
                .extension(fileUpload.getExtension())
                .mediaType(fileUpload.getMediaType())
                .size(fileUpload.getSize())
                .uri(String.format(
                        "%s/%s.%s",
                        stripTrailingSlash(baseUri),
                        fileUpload.getName(),
                        fileUpload.getExtension()
                ))
                .build();
    }

    private static String stripTrailingSlash(String value) {
        return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }
}
