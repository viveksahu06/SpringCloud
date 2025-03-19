package com.microservice.movie_catalog_service.resources;

import com.microservice.movie_catalog_service.models.CatalogItem;
import com.microservice.movie_catalog_service.models.Movie;
import com.microservice.movie_catalog_service.models.Rating;
import com.microservice.movie_catalog_service.models.UserRating;
import com.microservice.movie_catalog_service.services.MovieInfo;
import com.microservice.movie_catalog_service.services.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;
    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

       // WebClient.Builder builder = WebClient.builder();

        UserRating ratings = userRatingInfo.getUserRating(userId);
        return ratings.getRatings().stream().map(rating -> {
               //for each movie id call movie info service and get details
            return movieInfo.getCatalogItem(rating);
        }).collect(Collectors.toList());

    }
}
