package com.epsilon.MovieApp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epsilon.MovieApp.model.Movie;
import com.epsilon.MovieApp.model.MovieImage;
import com.epsilon.MovieApp.service.MovieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/movie_app")
@RequiredArgsConstructor
public class MovieController {

	private final MovieService movieService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public List<Movie> findAllMovies() {
		return movieService.findAllMovies();
	}
	
	@GetMapping("/images/sorted")
	@ResponseStatus(HttpStatus.OK)
	public List<MovieImage> findMoviesSortedByFavorites() {
		return movieService.findMoviesSortedByFavorites();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createMovie(@RequestBody Movie movie)
	{
		movieService.createMovie(movie);
	}
	
	
	@GetMapping("/images")
	public List<MovieImage> getMovieImagesByYear() {
		return movieService.getMovieImagesByYear();
	}
	@GetMapping("/images/{releaseYear}")
	public List<MovieImage> getMovieImagesByParticularYear(@PathVariable Integer releaseYear) {
		return movieService.getMovieImagesByParticularYear(releaseYear);
	}
	
	@PostMapping("/{movieId}/vote-up")
	public ResponseEntity<String> voteUp(@PathVariable Long movieId) {
		return movieService.voteUp(movieId);
	}

	@PostMapping("/{movieId}/vote-down")
	public ResponseEntity<String> voteDown(@PathVariable Long movieId) {
		return movieService.voteDown(movieId);
	}

	 

}
