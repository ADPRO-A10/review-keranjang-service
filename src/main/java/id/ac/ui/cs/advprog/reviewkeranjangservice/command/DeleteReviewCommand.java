package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;

public class DeleteReviewCommand implements ReviewCommand {
    private final String reviewId;
    private final ReviewRepository reviewRepository;

    public DeleteReviewCommand(String reviewId, ReviewRepository reviewRepository) {
        this.reviewId = reviewId;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void execute() {
        reviewRepository.delete(reviewId);
    }
}
