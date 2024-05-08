package id.ac.ui.cs.advprog.reviewkeranjangservice.repository;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ReviewRepositoryTest {

    @Mock
    ReviewRepository reviewRepository;

    List <Review> reviews;

    @BeforeEach
    void setUp() {

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
        Review review = reviews.get(0);
        Mockito.when(reviewRepository.save(review)).thenReturn(review);
        Mockito.when(reviewRepository.findById(review.getReviewId())).thenReturn(Optional.of(review));

        Review result = reviewRepository.save(review);

        Optional<Review> findResult = reviewRepository.findById(review.getReviewId());
        assertEquals(review.getReviewId(), result.getReviewId());
        assertEquals(review.getReviewId(), findResult.get().getReviewId());
        assertEquals(review.getReviewerName(), findResult.get().getReviewerName());
        assertEquals(review.getProduct(), findResult.get().getProduct());
        assertEquals(review.getReviewText(), findResult.get().getReviewText());
        assertEquals(review.getRating(), findResult.get().getRating());
    }

    @Test
    void testFindByIdIfIdFound() {
        Mockito.when(reviewRepository.saveAll(reviews)).thenReturn(reviews);
        Mockito.when(reviewRepository.findById(reviews.get(0).getReviewId())).thenReturn(Optional.of(reviews.get(0)));

        reviewRepository.saveAll(reviews);

        Optional<Review> findResult = reviewRepository.findById(reviews.get(0).getReviewId());
        assertEquals(reviews.get(0).getReviewId(), findResult.get().getReviewId());
        assertEquals(reviews.get(0).getReviewerName(), findResult.get().getReviewerName());
        assertEquals(reviews.get(0).getProduct(), findResult.get().getProduct());
        assertEquals(reviews.get(0).getReviewText(), findResult.get().getReviewText());
        assertEquals(reviews.get(0).getRating(), findResult.get().getRating());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        Mockito.when(reviewRepository.saveAll(reviews)).thenReturn(reviews);
        Mockito.when(reviewRepository.findById("aduhai")).thenReturn(Optional.empty());

        reviewRepository.saveAll(reviews);

        Optional<Review> findResult = reviewRepository.findById("aduhai");
        assertFalse(findResult.isPresent());
    }

    @Test
    void testDelete() {
        Mockito.when(reviewRepository.saveAll(reviews)).thenReturn(reviews);
        Mockito.doNothing().when(reviewRepository).deleteById(reviews.get(0).getReviewId());
        Mockito.when(reviewRepository.findById(reviews.get(0).getReviewId())).thenReturn(Optional.empty());

        reviewRepository.saveAll(reviews);

        reviewRepository.deleteById(reviews.get(0).getReviewId());

        Optional<Review> findResult = reviewRepository.findById(reviews.get(0).getReviewId());
        assertFalse(findResult.isPresent());
    }


}