package com.booking.Controller;

import com.booking.Dto.ReviewDto;
import com.booking.Repository.PropertyRepository;
import com.booking.Repository.ReviewRepository;
import com.booking.entity.Property;
import com.booking.entity.PropertyUser;
import com.booking.entity.Review;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")


public class ReviewController {
    private PropertyRepository propertyRepository;
private ReviewRepository reviewRepository;
    public ReviewController(PropertyRepository propertyRepository, ReviewRepository reviewRepository) {
        this.propertyRepository = propertyRepository;
        this.reviewRepository = reviewRepository;
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<String> addReview(@PathVariable long propertyId,
                                            @AuthenticationPrincipal PropertyUser user,
                                            @RequestBody ReviewDto reviewdto){
        Optional<Property> byId = propertyRepository.findById(propertyId);
        Property property = byId.get();
        Review r = reviewRepository.findReviewByUser(property, user);

        if(r!=null){
            return new ResponseEntity<>("already review",HttpStatus.INTERNAL_SERVER_ERROR);
        }


        Review review=new Review();
        review.setProperty(property);
review.setPropertyUser(user);
review.setContent(reviewdto.getContent());
reviewRepository.save(review);
return new ResponseEntity<>("Review added", HttpStatus.CREATED);

    }
}


