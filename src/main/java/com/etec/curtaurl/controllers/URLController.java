package com.etec.curtaurl.controllers;

import com.etec.curtaurl.models.URL;
import com.etec.curtaurl.models.URLResponse;
import com.etec.curtaurl.services.URLService;
import com.google.zxing.WriterException;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestController
public class URLController {

    @Autowired
    private URLService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<URLResponse> generateUrl(@RequestBody Map<String, String> request) throws IOException, WriterException {
        String originalUrl = request.get("longUrl");
        URL url = urlService.shortenUrl(originalUrl);

        String redirectUrl = "https://curtaurl-backend.onrender.com/r/" + url.getShortUrl();
        URLResponse response = new URLResponse(
                url.getUid(),
                url.getLongUrl(),
                redirectUrl,
                url.getQrCode(),
                url.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/r/{shortUrl}")
    public void redirectUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        URL url = urlService.getOriginalUrl(shortUrl);

        if (Objects.nonNull(url)) {
            response.sendRedirect(url.getLongUrl());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}