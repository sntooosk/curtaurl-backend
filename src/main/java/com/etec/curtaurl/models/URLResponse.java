package com.etec.curtaurl.models;

import java.time.LocalDateTime;

public record URLResponse(
        String uid,
        String longUrl,
        String shortUrl,
        QrCode qrCode,
        LocalDateTime createdAt
) {}
