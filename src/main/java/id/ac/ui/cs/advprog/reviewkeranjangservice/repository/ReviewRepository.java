package id.ac.ui.cs.advprog.reviewkeranjangservice.repository;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ReviewRepository {
    private final List<Review> reviewData = new ArrayList<>();
    public Review save (Review review) {
        int i = 0;
        for (Review savedReview : reviewData) {
            if (savedReview.getReviewId().equals(review.getReviewId())) {
                reviewData.remove(i);
                reviewData.add(i, review);
                return review;
            }
            i++;
        }

        reviewData.add(review);
        return review;
    }
    public Review findById (String id) {
        for (Review savedReview : reviewData) {
            if (savedReview.getReviewId().equals(id)) {
                return savedReview;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        for (Review savedReview : reviewData) {
            if (savedReview.getReviewId().equals(id)) {
                reviewData.remove(savedReview);
                return true;
            }
        }
        return false;
    }

    public Iterator<Review> findAll(){
        return reviewData.iterator();
    }
}
