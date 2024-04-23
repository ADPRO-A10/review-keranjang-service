package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest{
    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Lethal Company");
        product1.setProductQuantity(5);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("The Oultast Trials");
        product2.setProductQuantity(3);

        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateReview(){
        Product product = products.getFirst();
        Review review = new Review(product, "Yanto Laba-laba sunda",
                "Keren banget kang aduhai", 4);

        assertNotNull(review.getReviewId());
        assertEquals(product.getProductId(), review.getProduct().getProductId());
        assertEquals("Yanto Laba-laba sunda", review.getReviewerName());
        assertEquals("Keren banget kang aduhai", review.getReviewText());
        assertEquals(4, review.getRating());
    }

    @Test
    void testCreateReviewInvalidRating(){
        Product product = products.getFirst();
        assertThrows(IllegalArgumentException.class, () -> {
            Review review = new Review(product, "Yanto Laba-laba sunda",
                    "Keren banget kang aduhai", 6);
        });
    }
}