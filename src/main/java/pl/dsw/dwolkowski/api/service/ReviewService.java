package pl.dsw.dwolkowski.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dsw.dwolkowski.api.metadata.exception.EntityNotFoundException;
import pl.dsw.dwolkowski.api.metadata.model.Review;
import pl.dsw.dwolkowski.api.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Cacheable(cacheNames = "reviews_cache")
    public List<Review> getAllReviews(){ return reviewRepository.findAll(); }

    public List<Review> getReviewsByBook(long book_id){ return reviewRepository.findAllByBookId(book_id); }

    public Review getReview(long review_id){ return reviewRepository.findById(review_id).orElseThrow(EntityNotFoundException::new); }

    public Review createReview(Review newReview){ return reviewRepository.save(newReview); }

    public void deleteReview(long review_id){ reviewRepository.deleteById(review_id); }
}
