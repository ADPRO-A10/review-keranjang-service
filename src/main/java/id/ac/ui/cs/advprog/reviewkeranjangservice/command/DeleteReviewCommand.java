package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;

import java.util.Optional;


public class DeleteReviewCommand implements ReviewCommand {
    private final String reviewId;
    private final ReviewRepository reviewRepository;

    public DeleteReviewCommand(String reviewId, ReviewRepository reviewRepository) {
        this.reviewId = reviewId;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> execute() {
        Optional<Review> review = reviewRepository.findById(reviewId);
        review.ifPresent(value -> reviewRepository.deleteById(String.valueOf(value)));
        return review;
    }
}
