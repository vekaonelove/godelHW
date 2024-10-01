package edu.testTask.Godel.controller;

import edu.testTask.Godel.dto.ShortenUrlRequest;
import edu.testTask.Godel.dto.ShortenUrlResponse;
import edu.testTask.Godel.service.UrlShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request) {
        try {
            String shortenedUrl = urlShortenerService.shortenUrl(request.getOriginalUrl());
            return ResponseEntity.ok(new ShortenUrlResponse(shortenedUrl));
        } catch (Exception e) {
            //TODO logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ShortenUrlResponse("Error: Unable to shorten URL"));
        }
    }

    @GetMapping("/{shortenedId}")
    public ResponseEntity<Void> getOriginalUrl(@PathVariable String shortenedId) {
        String originalUrl = urlShortenerService.getOriginalUrl(shortenedId);
        if (originalUrl != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(originalUrl))
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
