package id.ac.ui.cs.advprog.reviewkeranjangservice.command;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Product;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.CreateReviewCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
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
        Mockito.when(reviewRepository.save(review)).thenReturn(review);
        CreateReviewCommand createReviewCommand = new CreateReviewCommand(review, reviewRepository);
        Optional<Review> result = createReviewCommand.execute();
        assertEquals(review, result.get());
    }
}
