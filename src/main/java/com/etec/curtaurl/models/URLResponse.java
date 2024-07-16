package com.etec.curtaurl.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class URLResponse{
        String uid;
        String longUrl;
        String shortUrl;
        QrCode qrCode;
        LocalDateTime createdAt;
}