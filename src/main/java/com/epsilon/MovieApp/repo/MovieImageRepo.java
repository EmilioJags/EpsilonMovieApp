package com.epsilon.MovieApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epsilon.MovieApp.model.MovieImage;

public interface MovieImageRepo extends JpaRepository<MovieImage, Long>{

}
