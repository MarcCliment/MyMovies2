package com.example.gfttraining.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;




@Service
public class MovieDBService {
	@Value("${themoviedatabase.api_key}")
	private String api_key;

	WebClient webClient = WebClient.create("https://api.themoviedb.org/3/");

	@SuppressWarnings("unchecked")
	public HashMap<String,Object> getConfig() {
		
		HashMap<String,Object> x = webClient.get()
				.uri(UriBuilder -> UriBuilder.path("configuration")
						.queryParam("api_key", "2cb9386c946854bb3a092c4af0e52ef9")
						.build())
				.retrieve()
				.bodyToMono(HashMap.class)
				.block();
		
		return x;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getMovie(String movie_id) {
		
		HashMap<String, Object> x = webClient.get()
				.uri(UriBuilder -> UriBuilder.path("movie/"+ movie_id)
						.queryParam("api_key", api_key)
						.build(movie_id))
				.retrieve()
				.bodyToMono(HashMap.class)
				.block();
		
		return x;
	}
	
	public HashMap<String, Object> findTopRatedMovies() throws IOException {
        
		HashMap<String, Object> x = webClient.get().uri(uriBuilder -> uriBuilder.path("/movie/top_rated")
				.queryParam("api_key", "2cb9386c946854bb3a092c4af0e52ef9")
				.build())
				.retrieve()
				.bodyToMono(HashMap.class)
				.block();
		return x;

    }
	
	public HashMap<String, Object> findPopularMovies() throws IOException{
		HashMap<String, Object> x = webClient.get().uri(uriBuilder -> uriBuilder.path("/movie/popular?api_key")
				.queryParam("api_key", "2cb9386c946854bb3a092c4af0e52ef9")
				.build())
				.retrieve()
				.bodyToMono(HashMap.class)
				.block();
		return x;
	}
	
}
