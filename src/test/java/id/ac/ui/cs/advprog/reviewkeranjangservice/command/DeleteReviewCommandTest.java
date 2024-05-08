package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Product;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.DeleteReviewCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DeleteReviewCommandTest {

    @Mock
    private ReviewRepository reviewRepository;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Lethal Company");
        product.setProductQuantity(5);
    }

    @Test
    void testDelete() {
        Review reviewToDelete = new Review(product, "Yanto Laba-laba sunda", "Keren banget kang aduhai", 4);
        Mockito.when(reviewRepository.save(reviewToDelete)).thenReturn(reviewToDelete);
        Mockito.when(reviewRepository.findById(reviewToDelete.getReviewId())).thenReturn(Optional.empty());

        DeleteReviewCommand deleteReviewCommand = new DeleteReviewCommand(reviewToDelete.getReviewId(), reviewRepository);
        deleteReviewCommand.execute();

        Optional<Review> deletedReview = reviewRepository.findById(reviewToDelete.getReviewId());
        assertFalse(deletedReview.isPresent());
    }

}
