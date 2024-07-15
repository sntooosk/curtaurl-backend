package com.etec.curtaurl.controllers;

import com.etec.curtaurl.models.URL;
import com.etec.curtaurl.models.URLResponse;
import com.etec.curtaurl.services.URLService;
import com.google.zxing.WriterException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Generate a shortened URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "URL successfully shortened"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/shorten")
    public ResponseEntity<URLResponse> generateUrl(@RequestBody Map<String, String> request) throws IOException, WriterException {
        String originalUrl = request.get("longUrl");
        URL url = urlService.shortenUrl(originalUrl);

        String redirectUrl = "https://localhost:8081/r/" + url.getShortUrl();
        URLResponse response = new URLResponse(
                url.getUid(),
                url.getLongUrl(),
                redirectUrl,
                url.getQrCode(),
                url.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Redirect to the original URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Successfully redirected"),
            @ApiResponse(responseCode = "404", description = "URL not found")
    })
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
