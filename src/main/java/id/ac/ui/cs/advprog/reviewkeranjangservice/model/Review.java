package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    String ReviewId;
    Product product;
    String reviewerName;
    String reviewText;
    int rating;

    public Review(Product product, String reviewerName, String reviewText, int rating) {
    }
}