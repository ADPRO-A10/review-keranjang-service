package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.command.ReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Optional<Review> executeCommand(ReviewCommand command) {
        return command.execute();
    }

    @Override
    public List<Review> findAll() {
        Iterable<Review> productIterator = reviewRepository.findAll();
        List<Review> allProduct = new ArrayList<>();
        productIterator.forEach(allProduct::add);
        return allProduct;
    }

}