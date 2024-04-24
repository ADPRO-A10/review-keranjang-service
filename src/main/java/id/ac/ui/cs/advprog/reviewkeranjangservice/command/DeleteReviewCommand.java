package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;

public class DeleteReviewCommand implements ReviewCommand {
    public DeleteReviewCommand(String reviewId, ReviewRepository reviewRepository) {
    }

    @Override
    public void execute() {
    }
}
