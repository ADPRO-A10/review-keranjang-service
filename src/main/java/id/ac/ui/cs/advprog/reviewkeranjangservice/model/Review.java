package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class Review {
    String ReviewId;
    Product product;
    String reviewerName;
    String reviewText;
    int rating;

    public Review(Product product, String reviewerName, String reviewText, int rating) {
        this.ReviewId = UUID.randomUUID().toString();
        this.product = product;
        this.reviewerName = reviewerName;
        this.reviewText = reviewText;
        setRating(rating);
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating tidak valid");
        }
        this.rating = rating;
    }
}