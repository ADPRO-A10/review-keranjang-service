package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;

public class EditReviewCommand implements ReviewCommand {

    public EditReviewCommand(ReviewRepository reviewRepository, Review newReview) {
    }

    @Override
    public void execute() {
    }
}