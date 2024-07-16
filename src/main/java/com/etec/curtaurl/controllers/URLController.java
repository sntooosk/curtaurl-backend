package com.etec.curtaurl.controllers;

import com.etec.curtaurl.models.URL;
import com.etec.curtaurl.models.URLRequest;
import com.etec.curtaurl.models.URLResponse;
import com.etec.curtaurl.services.URLService;
import com.google.zxing.WriterException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@Tag(name = "URL Shortening API", description = "API for shortening URLs and redirecting to the original URLs")
@RestController
public class URLController {

    @Autowired
    private URLService urlService;

    @Operation(summary = "Generate a shortened URL", 
               description = "Generates a shortened URL from the provided long URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "URL successfully shortened", 
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = URLResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/shorten")
    public ResponseEntity<URLResponse> generateUrl(@RequestBody @Schema(description = "Request body to generate shortened URL", required = true) URLRequest request) throws IOException, WriterException {
        String originalUrl = request.longUrl();
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

    @Operation(summary = "Redirect to the original URL", 
               description = "Redirects to the original long URL using the shortened URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Successfully redirected"),
            @ApiResponse(responseCode = "404", description = "URL not found")
    })
    @GetMapping("/r/{shortUrl}")
    public void redirectUrl(@PathVariable @Schema(description = "Shortened URL", required = true) String shortUrl, HttpServletResponse response) throws IOException {
        URL url = urlService.getOriginalUrl(shortUrl);

        if (Objects.nonNull(url)) {
            response.sendRedirect(url.getLongUrl());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
