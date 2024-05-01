package id.ac.ui.cs.advprog.reviewkeranjangservice.repository;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepository {
    private final List<Review> orderData = new ArrayList<>();
    public Review save (Review review) {
        int i = 0;
        for (Review savedReview : orderData) {
            if (savedReview.getReviewId().equals(review.getReviewId())) {
                orderData.remove(i);
                orderData.add(i, review);
                return review;
            }
            i++;
        }

        orderData.add(review);
        return review;
    }
    public Review findById (String id) {
        for (Review savedReview : orderData) {
            if (savedReview.getReviewId().equals(id)) {
                return savedReview;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        for (Review savedReview : orderData) {
            if (savedReview.getReviewId().equals(id)) {
                orderData.remove(savedReview);
                return true;
            }
        }
        return false;
    }
}