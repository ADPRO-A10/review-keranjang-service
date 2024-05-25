package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Review {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(columnDefinition = "VARCHAR(255)")
    String ReviewId;
    @ManyToOne
    Product product;
    String reviewerName;
    String reviewText;
    int rating;

    public Review(Product product, String reviewerName, String reviewText, int rating) {
        this();
        this.product = product;
        this.reviewerName = reviewerName;
        this.reviewText = reviewText;
        setRating(rating);
    }

    public Review() {
        this.ReviewId = UUID.randomUUID().toString();
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating tidak valid");
        }
        this.rating = rating;
    }
}
