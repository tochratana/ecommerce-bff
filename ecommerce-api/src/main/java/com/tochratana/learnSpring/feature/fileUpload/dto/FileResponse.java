package com.tochratana.learnSpring.feature.fileUpload.dto;

import lombok.Builder;

@Builder
public record FileResponse(
        String name,
        String extension,
        Long size,
        String mediaType,
        String uri
) {
}
