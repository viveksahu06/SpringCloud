package com.microservice.movie_catalog_service.services;

import com.microservice.movie_catalog_service.models.Rating;
import com.microservice.movie_catalog_service.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@Service
public class UserRatingInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod = "getFallbackUserRating",
            ignoreExceptions = { IllegalStateException.class },  // 🔥 Important: Catch Eureka "No instances available" error
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            }
    )
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, UserRating.class);
    }

    // Fallback method
    public UserRating getFallbackUserRating(String userId) {
        System.out.println("Fallback method called: No instances available for rating-data-service.");

        // Return default response
        UserRating fallbackRating = new UserRating();
        fallbackRating.setUserId(userId);
        fallbackRating.setRatings(Collections.emptyList()); // Return empty list
        return fallbackRating;
    }

}
