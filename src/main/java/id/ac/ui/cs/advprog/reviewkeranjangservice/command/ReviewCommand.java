package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;

import java.util.Optional;

public interface ReviewCommand {
    Optional<Review> execute();
}