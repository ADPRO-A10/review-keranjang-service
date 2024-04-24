package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;

public class CreateReviewCommand implements ReviewCommand {
    private final Review review;
    private final ReviewRepository reviewRepository;

    public CreateReviewCommand(Review review, ReviewRepository reviewRepository) {
        this.review = review;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void execute() {
        reviewRepository.save(review);
    }
}
