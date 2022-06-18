package pl.dsw.dwolkowski.api.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.dsw.dwolkowski.api.metadata.model.Review;
import pl.dsw.dwolkowski.api.service.ReviewService;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/library/v1/review")
@Api(tags = "Review Management")
@Tag(name = "Review Management", description = "")
public class ReviewController {

    private final ReviewService reviewService;

    // GET MAPPINGS
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = "Returns all reviews from database." )
    public Collection<Review> getAllReviews(){ return reviewService.getAllReviews(); }

    @GetMapping(path = "/book/{book_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = "Returns reviews for given book_id." )
    @ApiResponse(responseCode = "404", description = "Review Not Found")
    public List<Review> getReviewByBook(@PathVariable long book_id){
        return reviewService.getReviewsByBook(book_id);
    }

    @GetMapping(path = "/{review_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = "Returns review with given review_id." )
    @ApiResponse(responseCode = "404", description = "Review Not Found")
    public Review getReview(@PathVariable long review_id){
        return reviewService.getReview(review_id);
    }

    // POST MAPPINGS
    @PostMapping(
            path = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation( summary = "Adds review given in JSON format to database." )
    public Review createReview(@RequestBody Review newReview){
        return reviewService.createReview(newReview);
    }

    // DELETE MAPPINGS
    @DeleteMapping(path = "/{review_id}")
    @Operation( summary = "Deletes review from database." )
    @ApiResponse(responseCode = "404", description = "Review Not Found")
    public void deleteReview(@PathVariable long review_id){
        reviewService.deleteReview(review_id);
    }
}
