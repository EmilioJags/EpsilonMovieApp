package com.epsilon.MovieApp.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="t_movie_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String movieName;
	private String imageUrl;
	private Integer favorites;
	private String details;
}
