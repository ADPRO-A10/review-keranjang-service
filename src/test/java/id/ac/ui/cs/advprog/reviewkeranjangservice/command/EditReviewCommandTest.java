package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Product;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.EditReviewCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditReviewCommandTest {

    private ReviewRepository reviewRepository;
    private Product product;

    @BeforeEach
    void setUp() {
        reviewRepository = new ReviewRepository();
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Lethal Company");
        product.setProductQuantity(5);
    }

    @Test
    void testEdit() {
        Review reviewToEdit = new Review(product, "Yanto Laba-laba sunda", "Keren banget kang aduhai", 4);
        reviewRepository.save(reviewToEdit);

        reviewToEdit.setRating(5);
        EditReviewCommand editReviewCommand = new EditReviewCommand(reviewRepository, reviewToEdit);
        editReviewCommand.execute();

        Review updatedReview = reviewRepository.findById(reviewToEdit.getReviewId());
        assertEquals(5, updatedReview.getRating());
    }
}