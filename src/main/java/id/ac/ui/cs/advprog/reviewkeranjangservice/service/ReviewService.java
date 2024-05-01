package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.command.ReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;

import java.util.List;

public interface ReviewService {
    Review executeCommand(ReviewCommand command);
    List<Review> findAll();
}