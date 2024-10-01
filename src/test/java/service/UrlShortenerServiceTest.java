package service;

import edu.testTask.Godel.dao.UrlEntity;
import edu.testTask.Godel.dao.UrlShortenerRepository;
import edu.testTask.Godel.service.UrlShortenerService;
import edu.testTask.Godel.service.exception.UrlNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UrlShortenerServiceTest {

    @InjectMocks
    private UrlShortenerService urlShortenerService;
    @Mock
    private UrlShortenerRepository urlShortenerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShortenUrl() {
        String originalUrl = "https://example.com";
        String shortenedUrl = urlShortenerService.shortenUrl(originalUrl);
        assertNotNull(shortenedUrl);
        assertTrue(shortenedUrl.startsWith("http://shortUr.ls/"));
        verify(urlShortenerRepository, times(1)).save(any(UrlEntity.class));
    }


    @Test
    public void testGetOriginalUrlWhenNotFound() {
        String uniqueId = "nonexistentId";
        when(urlShortenerRepository.findById(uniqueId)).thenReturn(Optional.empty());
        assertThrows(UrlNotFoundException.class, () -> {
            urlShortenerService.getOriginalUrl(uniqueId);
        });
        verify(urlShortenerRepository, times(1)).findById(uniqueId);
    }

    @Test
    public void testGetOriginalUrl() {
        String originalUrl = "https://example.com";
        String uniqueId = "abcdef";
        UrlEntity urlEntity = new UrlEntity(uniqueId, originalUrl);

        when(urlShortenerRepository.findById(uniqueId)).thenReturn(Optional.of(urlEntity));
        String retrievedUrl = urlShortenerService.getOriginalUrl(uniqueId);
        assertEquals(originalUrl, retrievedUrl);
        verify(urlShortenerRepository, times(1)).findById(uniqueId);
    }
}
