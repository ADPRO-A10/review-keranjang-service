package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteReviewCommand implements ReviewCommand {
    private final String reviewId;
    private final ReviewRepository reviewRepository;

    public DeleteReviewCommand(String reviewId, ReviewRepository reviewRepository) {
        this.reviewId = reviewId;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review execute() {
        Review review = reviewRepository.findById(reviewId);
        if (review != null) {
            reviewRepository.delete(review.getReviewId());
        }
        return review;
    }
}
