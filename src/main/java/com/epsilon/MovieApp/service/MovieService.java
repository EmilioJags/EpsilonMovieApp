package com.epsilon.MovieApp.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epsilon.MovieApp.model.Movie;
import com.epsilon.MovieApp.model.MovieImage;
import com.epsilon.MovieApp.repo.MovieRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

	private final MovieRepo movieRepo;
	
	public List<Movie> findAllMovies(){
		List<Movie> list = movieRepo.findAll();
		return list;
	}

	public List<MovieImage> getMovieImagesByYear() {
		List<Movie> movieList = movieRepo.findAll();
		Collections.sort(movieList,  new Comparator<Movie>() {

	        public int compare(Movie o1, Movie o2) { 
	            return o2.getReleaseYear().compareTo(o1.getReleaseYear());
	        }
	    });
		List<MovieImage> imageList = movieList.stream().map(m -> m.getMovieImage()).collect(Collectors.toList());
		return imageList;
	}

	public List<MovieImage> getMovieImagesByParticularYear(Integer yearFilter) {
		List<Movie> movieList = movieRepo.findAll();
		List<MovieImage> imageList = movieList.stream().filter(m -> m.getReleaseYear() == yearFilter).map(m -> m.getMovieImage()).toList();		
		return imageList;
	}

	public ResponseEntity<String> voteUp(Long movieId) {
		Optional<Movie> movie = movieRepo.findById(movieId);
		
		if(!movie.isPresent()) return new ResponseEntity<String>("The movie does not exist",  HttpStatus.OK);
		movie.get().getMovieImage().setFavorites(movie.get().getMovieImage().getFavorites() + 1);
		movieRepo.save(movie.get());
		return new ResponseEntity<String>("The movie vote has been recorded. Current votes for '" + movie.get().getMovieName() + "'  : [" + movie.get().getMovieImage().getFavorites() + "].",  HttpStatus.OK);
	}
	
	public ResponseEntity<String> voteDown(Long movieId) {
		Optional<Movie> movie = movieRepo.findById(movieId);
		
		if(!movie.isPresent()) return new ResponseEntity<String>("The movie does not exist",  HttpStatus.OK);
		movie.get().getMovieImage().setFavorites(movie.get().getMovieImage().getFavorites() - 1);
		movieRepo.save(movie.get());
		return new ResponseEntity<String>("The movie vote has been recorded. Current votes for '" + movie.get().getMovieName() + "' : [" + movie.get().getMovieImage().getFavorites() + "].",  HttpStatus.OK);
	}

	public void createMovie(Movie movie) { 
		Movie newMovie = Movie.builder()
					.movieName(movie.getMovieName())
					.details(movie.getDetails())
					.releaseYear(movie.getReleaseYear())
					.movieImage(movie.getMovieImage())
					.build(); 
		movieRepo.save(newMovie);
	}

	public List<MovieImage> findMoviesSortedByFavorites() {
		List<Movie>  list = movieRepo.findAll();		
		List<MovieImage> imageList = list.stream().map(movie -> movie.getMovieImage()).toList();
		Collections.sort(imageList,  new Comparator<MovieImage>() {

	        public int compare(MovieImage o1, MovieImage o2) { 
	            return o2.getFavorites().compareTo(o1.getFavorites());
	        }
	    });
		return imageList;
	}

	public MovieImage findById(Long movieImageId) {
		List<Movie> movie = movieRepo.findAll();
		Optional<MovieImage> movieImage = movie.stream().filter(m -> m.getMovieImage().getId() ==  movieImageId).findFirst().map(m -> m.getMovieImage());
		if(movieImage.isPresent()) return movieImage.get();
		return null;
	}
	
}
