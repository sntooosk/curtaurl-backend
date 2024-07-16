package com.etec.curtaurl.models;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record URLResponse(
        String uid,
        String longUrl,
        String shortUrl,
        QrCode qrCode,
        LocalDateTime createdAt
        ){}
