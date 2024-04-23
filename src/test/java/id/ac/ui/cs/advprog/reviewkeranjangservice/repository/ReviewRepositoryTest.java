package id.ac.ui.cs.advprog.reviewkeranjangservice.repository;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReviewRepositoryTest {

    ReviewRepository reviewRepository;

    List <Review> reviews;

    @BeforeEach
    void setUp() {
        reviewRepository = new ReviewRepository();

        List <Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Lethal Company");
        product1.setProductQuantity(5);
        products.add(product1);

        reviews = new ArrayList<>();

        Product product = products.getFirst();

        Review review1 = new Review(product, "Yanto Laba-laba sunda", "Keren banget kang aduhai", 4);
        reviews.add(review1);

        Review review2 = new Review(product, "Fuad tikungan detos", "Langsung aku skrinsot wlee :p", 3);
        reviews.add(review2);

        Review review3 = new Review(product, "Xin fu tang 610 mantan", "Aku marah", 2);
        reviews.add(review3);
    }

    @Test

    void testSaveCreate(){
        Review review = reviews.getFirst();
        Review result = reviewRepository.save(review);

        Review findResult = reviewRepository.findById(reviews.getFirst().getReviewId());
        assertEquals(review.getReviewId(), result.getReviewId());
        assertEquals(review.getReviewId(), findResult.getReviewId());
        assertEquals(review.getReviewerName(), findResult.getReviewerName());
        assertEquals(review.getProduct(), findResult.getProduct());
        assertEquals(review.getReviewText(), findResult.getReviewText());
        assertEquals(review.getRating(), findResult.getRating());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Review review : reviews) {
            reviewRepository.save(review);
        }

        Review findResult = reviewRepository.findById(reviews.getFirst().getReviewId());
        assertEquals(reviews.getFirst().getReviewId(), findResult.getReviewId());
        assertEquals(reviews.getFirst().getReviewerName(), findResult.getReviewerName());
        assertEquals(reviews.getFirst().getProduct(), findResult.getProduct());
        assertEquals(reviews.getFirst().getReviewText(), findResult.getReviewText());
        assertEquals(reviews.getFirst().getRating(), findResult.getRating());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Review review : reviews) {
            reviewRepository.save(review);
        }

        Review findResult = reviewRepository.findById("aduhai");
        assertNull(findResult);
    }
}