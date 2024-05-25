package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;

import java.util.Optional;

public class EditReviewCommand implements ReviewCommand {
    private final ReviewRepository reviewRepository;
    private final Review newReview;

    public EditReviewCommand(ReviewRepository reviewRepository, Review newReview) {
        this.reviewRepository = reviewRepository;
        this.newReview = newReview;
    }

    @Override
    public Optional<Review> execute() {
        Optional<Review> existingReview = reviewRepository.findById(newReview.getReviewId());
        if (existingReview.isPresent()) {
            Review reviewToUpdate = existingReview.get();
            reviewToUpdate.setReviewerName(newReview.getReviewerName());
            reviewToUpdate.setReviewText(newReview.getReviewText());
            reviewToUpdate.setRating(newReview.getRating());
            reviewRepository.save(reviewToUpdate);
        }
        return existingReview;
    }

}