package com.epsilon.MovieApp;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epsilon.MovieApp.model.MovieImage;
import com.epsilon.MovieApp.service.MovieService;

@ExtendWith(MockitoExtension.class)
public class MovieAppApplicationTests {
 
	
    @InjectMocks
    private MovieService movieService;

    @Test
    public void testGetMovieImageById() {
        // Mocking
        Long movieImageId = 1L;
        MovieImage mockMovieImage = new MovieImage();
        mockMovieImage.setId(movieImageId);
        mockMovieImage.setImageUrl("https://example.com/image.jpg");
        mockMovieImage.setFavorites(10);

        when(movieService.findById(movieImageId)).thenReturn(mockMovieImage);

        // Testing
        MovieImage movieImage = movieService.findById(movieImageId);

        // Verification
        assertNotNull(movieImage);
        assertEquals(movieImageId, movieImage.getId());
        assertEquals("https://example.com/image.jpg", movieImage.getImageUrl());
        assertEquals(10, movieImage.getFavorites());
    }

    // Add more test cases as needed
}
;