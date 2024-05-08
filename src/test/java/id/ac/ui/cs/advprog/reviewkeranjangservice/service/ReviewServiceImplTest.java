package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.command.ReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Product;

import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    ReviewServiceImpl reviewService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Lethal Company");
        product.setProductQuantity(5);
    }

    @Test
    void testExecuteCommand() {
        Review review = new Review(product, "Yanto Laba-laba sunda", "Keren banget kang aduhai", 4);
        ReviewCommand command = () -> java.util.Optional.of(review);

        Optional<Review> result = reviewService.executeCommand(command);

        assertEquals(review, result.get());
    }

    @Test
    void testFindAllIfEmpty() {
        List<Review> reviewList = new ArrayList<>();
        Mockito.when(reviewRepository.findAll()).thenReturn(reviewList);

        List<Review> products = reviewService.findAll();

        assertTrue(products.isEmpty());
    }

    @Test
    void testFindAllWithReviews() {
        List<Review> reviewList = new ArrayList<>();
        Review review1 = new Review(product, "John Doe", "Great product!", 5);
        Review review2 = new Review(product, "Jane Smith", "Not bad, but could be better", 3);
        reviewList.add(review1);
        reviewList.add(review2);

        Mockito.when(reviewRepository.findAll()).thenReturn(reviewList);

        List<Review> reviews = reviewService.findAll();

        assertFalse(reviews.isEmpty());
        assertEquals(2, reviews.size());
        assertTrue(reviews.contains(review1));
        assertTrue(reviews.contains(review2));
    }
}