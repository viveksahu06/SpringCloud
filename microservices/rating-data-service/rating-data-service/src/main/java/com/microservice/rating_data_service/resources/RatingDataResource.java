package com.microservice.rating_data_service.resources;

import com.microservice.rating_data_service.models.Rating;
import com.microservice.rating_data_service.models.UserRating;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource {
    @RequestMapping("/movies/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId ,4);
    }
    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
//        List<Rating> ratings = Arrays.asList(
//                new Rating("100", 4),
//                new Rating("120", 3),
//                new Rating("100",6)
//        );
        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;
    }

}
