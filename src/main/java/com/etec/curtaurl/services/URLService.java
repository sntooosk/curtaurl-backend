package com.etec.curtaurl.services;

import com.etec.curtaurl.models.URL;
import com.etec.curtaurl.repositories.URLRepository;
import com.google.zxing.WriterException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class URLService {

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private QrCodeService qrCodeService;

    public String generateRandomUrl() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }

    public URL shortenUrl(String originalUrl) throws IOException, WriterException {
        URL url = new URL();
        url.setLongUrl(originalUrl);
        url.setShortUrl(generateRandomUrl());
        url.setCreatedAt(LocalDateTime.now());
        url.setQrCode(qrCodeService.generateQrCode(originalUrl));
        return urlRepository.save(url);
    }

    public URL getOriginalUrl(String shortUrl) {
        try {
            return urlRepository.findByShortUrl(shortUrl);
        } catch (Exception e) {
            throw new RuntimeException("URL not found in our records", e);
        }
    }
}