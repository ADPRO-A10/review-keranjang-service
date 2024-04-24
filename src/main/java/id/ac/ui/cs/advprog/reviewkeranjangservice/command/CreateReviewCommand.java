package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;

public class CreateReviewCommand implements ReviewCommand {

    public CreateReviewCommand(Review review, ReviewRepository reviewRepository) {
    }

    @Override
    public void execute() {
    }
}
