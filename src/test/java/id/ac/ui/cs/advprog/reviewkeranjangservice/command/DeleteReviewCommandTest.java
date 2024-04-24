package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Product;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.DeleteReviewCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DeleteReviewCommandTest {

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
    void testDelete() {
        Review reviewToDelete = new Review(product, "Yanto Laba-laba sunda", "Keren banget kang aduhai", 4);
        reviewRepository.save(reviewToDelete);

        DeleteReviewCommand deleteReviewCommand = new DeleteReviewCommand(reviewToDelete.getReviewId(), reviewRepository);
        deleteReviewCommand.execute();

        Review deletedReview = reviewRepository.findById(reviewToDelete.getReviewId());
        assertNull(deletedReview);
    }

}
