package com.example.gfttraining.Controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gfttraining.Entity.UserMovie;
import com.example.gfttraining.Repository.UserMovieRepository;
import com.example.gfttraining.Service.MovieDBService;

@RestController
public class MovieController {
	
	@Autowired
	MovieDBService movieService;
	
	@Autowired
	UserMovieRepository userMovieRepository;

	@GetMapping ("/api/configuration")
	public Object getConfiguration() {
	   var conf  = movieService.getConfig();
	    return conf ;
	}
	
	@GetMapping("/api/genre/movie/list")
    public HashMap<String, Object> getAllGenres() {
        HashMap<String, Object> config = movieService.getAllGenres();

        return config;
    }
	
	@GetMapping("/api/movie/top_rated")
	public HashMap<String, Object> returnTopRatedMovies() throws IOException{
		HashMap<String, Object> movie = movieService.getTopRated();
		return movie;
	}
	
	@GetMapping ("/api/movie/popular")
	public HashMap<String, Object> returnPopularMovies() throws IOException{
		HashMap<String, Object> movie = movieService.getPopular();
		return movie;
	}
	
	@GetMapping("api/movie/{movie_id}")
    public HashMap<String, Object> getMovieById(@AuthenticationPrincipal UserDetails user, @PathVariable Integer movie_id){

        UserMovie userMovie = userMovieRepository.findByUsernameAndMovie(user.getUsername(),movie_id.toString()).orElse(null);
        HashMap<String, Object> movie = movieService.getMovieById(movie_id);

        if(userMovie != null){
            movie.put("favorite",userMovie.getFavorite());
            movie.put("personal_rating",userMovie.getPersonal_rating());
            movie.put("notes",userMovie.getNotes());
        }

        return movie;
    }

    @PatchMapping("api/movie/{movie_id}")
    public ResponseEntity<UserMovie> putFavoritePersonalRatingNotes(@PathVariable Integer movie_id, @RequestBody UserMovie newUserMovie,@AuthenticationPrincipal UserDetails user){

        UserMovie updatedMovie = userMovieRepository.findByUsernameAndMovie(user.getUsername(),movie_id.toString()).orElse(null);
        if(updatedMovie == null){
            updatedMovie = new UserMovie();
        }
        updatedMovie.setUsername(user.getUsername());
        updatedMovie.setMovie(movie_id.toString());
        updatedMovie.setFavorite(newUserMovie.getFavorite());
        updatedMovie.setPersonal_rating(newUserMovie.getPersonal_rating());
        updatedMovie.setNotes((newUserMovie.getNotes()));

        userMovieRepository.save(updatedMovie);

        return new ResponseEntity<UserMovie>(updatedMovie, HttpStatus.OK);
    }

    @GetMapping("api/movie/{movie_id}/credits")
    public HashMap<String, Object> getCastAndCrew(@PathVariable Integer movie_id){
        HashMap<String, Object> config = movieService.getCastAndCrew(movie_id);

        return config;
    }
    @GetMapping("api/movie/{movie_id}/images")
    public HashMap<String, Object> getImages(@PathVariable Integer movie_id){
        HashMap<String, Object> config = movieService.getAllImages(movie_id);

        return config;
    }
    @GetMapping("api/movie/{movie_id}/keywords")
    public HashMap<String, Object> getKeywords(@PathVariable Integer movie_id){
        HashMap<String, Object> config = movieService.getKeywords(movie_id);

        return config;
    }
    @GetMapping("api/movie/{movie_id}/recommendations")
    public HashMap<String, Object> getRecommendations(@PathVariable Integer movie_id){
        HashMap<String, Object> config = movieService.getRecommendations(movie_id);

        return config;
    }
    @GetMapping("api/movie/{movie_id}/similar")
    public HashMap<String, Object> getSimilar(@PathVariable Integer movie_id){
        HashMap<String, Object> config = movieService.getSimilarMovies(movie_id);

        return config;
    }
}
