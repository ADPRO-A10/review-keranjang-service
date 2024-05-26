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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private RestTemplate restTemplate;

    private Product product;

    @Mock
    ReviewCommand reviewCommand;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Lethal Company");
        product.setProductQuantity(5);

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        reviewService = new ReviewServiceImpl(reviewRepository, restTemplateBuilder);
    }

    @Test
    void testExecuteCommand() {
        Review review = new Review(product, "Yanto Laba-laba sunda", "Keren banget kang aduhai", 4);
        Mockito.when(reviewCommand.execute()).thenReturn(Optional.of(review));

        Optional<Review> result = reviewService.executeCommand(reviewCommand);

        assertTrue(result.isPresent());
    }

    @Test
    void testFindAll() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(product, "Yanto Laba-laba sunda", "Keren banget kang aduhai", 4));
        reviews.add(new Review(product, "Yanto Laba-laba jawa", "Keren banget kang aduhai", 3));

        Mockito.when(reviewRepository.findAll()).thenReturn(reviews);

        List<Review> result = reviewService.findAll();

        assertEquals(2, result.size());
    }



}