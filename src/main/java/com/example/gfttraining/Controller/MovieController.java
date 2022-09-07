package com.example.gfttraining.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gfttraining.Entity.UserMovie;
import com.example.gfttraining.Repository.UserMovieRepository;
import com.example.gfttraining.Service.MovieDBService;

@RestController
@RequestMapping("/api")
public class MovieController {
	
	@Autowired
	MovieDBService movieService;
	
	@Autowired
	UserMovieRepository userMovieRepository;

	@GetMapping ("/configuration")
	public Object getConfiguration() {
	   var conf  = movieService.getConfig() ;
	    return conf ;
	}
	
	@GetMapping ("/movie/{movieid}")
	public Object getMovie (
	        @AuthenticationPrincipal UserDetails user ,
	        @PathVariable Integer movie_id
			) {
		UserMovie userMovie = userMovieRepository.findByUsernameAndMovie(user.getUsername(), movie_id).orElse(null);
		HashMap<String, Object> movie = movieService.getMovie(movie_id.toString());
		movie.put("Hello", "World");
		if(userMovie != null) {
			movie.put("favorite", userMovie.getFavorite());
			movie.put("personal_rating", userMovie.getPersonal_rating());
			movie.put("notes", userMovie.getNotes());
		}
		return movie;
	}
}
