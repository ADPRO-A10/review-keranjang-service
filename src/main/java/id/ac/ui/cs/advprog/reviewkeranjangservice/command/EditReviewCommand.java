package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;

public class EditReviewCommand implements ReviewCommand {
    private final ReviewRepository reviewRepository;
    private final Review newReview;

    public EditReviewCommand(ReviewRepository reviewRepository, Review newReview) {
        this.reviewRepository = reviewRepository;
        this.newReview = newReview;
    }

    @Override
    public void execute() {
        Review existingReview = reviewRepository.findById(newReview.getReviewId());
        if (existingReview != null) {
            reviewRepository.save(newReview);
        }
    }
}