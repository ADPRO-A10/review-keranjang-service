package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.command.ReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    private final RestTemplate restTemplate;

    public ReviewServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

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

    @Override
    public Review getReviewByProduct(String productId) {
        String productApi = "http://34.143.169.251";
        String url = productApi + "/reviews/" + productId;
        ResponseEntity<Review> response = restTemplate.getForEntity(url, Review.class);
        return response.getBody();
    }
}