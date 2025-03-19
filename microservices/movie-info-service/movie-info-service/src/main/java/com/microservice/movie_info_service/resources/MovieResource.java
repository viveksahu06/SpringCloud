package com.microservice.movie_info_service.resources;

import com.microservice.movie_info_service.models.Movie;
import com.microservice.movie_info_service.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {
    @Value("${api.key}")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
            String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
            MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);
            return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}
