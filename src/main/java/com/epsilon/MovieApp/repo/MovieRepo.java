package com.epsilon.MovieApp.repo;

import com.epsilon.MovieApp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long>{

}
