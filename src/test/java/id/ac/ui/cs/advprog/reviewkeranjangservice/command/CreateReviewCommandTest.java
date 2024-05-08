package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Product;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.CreateReviewCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateReviewCommandTest {

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
    void testExecuteCreate() {
        Review review = new Review(product, "Yanto Laba-laba sunda", "Keren banget kang aduhai", 4);
        CreateReviewCommand createReviewCommand = new CreateReviewCommand(review, reviewRepository);
        createReviewCommand.execute();

        Optional<Review> savedReview = reviewRepository.findById(review.getReviewId());
        assertNotNull(savedReview);
        assertEquals(review.getReviewerName(), savedReview.get().getReviewerName());
        assertEquals(review.getReviewText(), savedReview.get().getReviewText());
        assertEquals(review.getRating(), savedReview.get().getRating());
    }
}
