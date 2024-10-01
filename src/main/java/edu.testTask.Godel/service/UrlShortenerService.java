package edu.testTask.Godel.service;

import edu.testTask.Godel.dao.UrlEntity;
import edu.testTask.Godel.dao.UrlShortenerRepository;
import edu.testTask.Godel.service.exception.UrlNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlShortenerService {
    private static final int SHORTENED_URL_SIZE = 6;
    private final UrlShortenerRepository urlShortenerRepository;

    @Autowired
    public UrlShortenerService(UrlShortenerRepository urlShortenerRepository) {
        this.urlShortenerRepository = urlShortenerRepository;
    }

    public String shortenUrl(String originalUrl) {
        String uniqueID = generateUniqueId(originalUrl);
        UrlEntity urlEntity = new UrlEntity(originalUrl, uniqueID);
        urlShortenerRepository.save(urlEntity);
        String shortenedUrl = "http://shortUr.ls/" + uniqueID;
        return shortenedUrl;
    }

    public String getOriginalUrl(String shortenedId) {
        Optional<UrlEntity> urlEntity = urlShortenerRepository.findById(shortenedId);
        String originalUrl = urlEntity.map(UrlEntity::getOriginalUrl)
                .orElseThrow(() -> new UrlNotFoundException(
                        "Shortened URL not found for this ID: " + shortenedId));
        return originalUrl;
    }

    private String generateUniqueId(String url) {
        return UUID.randomUUID().toString().substring(0, SHORTENED_URL_SIZE);
    }
}