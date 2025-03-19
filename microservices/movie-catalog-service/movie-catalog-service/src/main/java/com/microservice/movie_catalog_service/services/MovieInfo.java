package com.microservice.movie_catalog_service.services;

import com.microservice.movie_catalog_service.models.CatalogItem;
import com.microservice.movie_catalog_service.models.Movie;
import com.microservice.movie_catalog_service.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {
    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie=restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

//           Movie movie=webClientBuilder.build()
//                   .get()
//                   .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                   .retrieve()
//                   .bodyToMono(Movie.class)
//                   .block();
        //put them all together
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }
    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie name not found" , "", rating.getRating());
    }
}
