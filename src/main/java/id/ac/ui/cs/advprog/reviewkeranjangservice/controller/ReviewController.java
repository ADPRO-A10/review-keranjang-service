package id.ac.ui.cs.advprog.reviewkeranjangservice.controller;

import id.ac.ui.cs.advprog.reviewkeranjangservice.command.CreateReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.DeleteReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.EditReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.ReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;
import id.ac.ui.cs.advprog.reviewkeranjangservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public String renderReviewPage() {
        return "Hello World Review!!";
    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody Review review){
        Map<String, Object> res = new HashMap<>();
        try{
            ReviewRepository reviewRepository = new ReviewRepository();
            ReviewCommand createReview = new CreateReviewCommand(review, reviewRepository);
            reviewService.executeCommand(createReview);

            res.put("review", createReview);
            res.put("message", "Review Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }catch (Exception e){
            res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.put("error", e.getMessage());
            res.put("message", "Something Wrong With Server");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") String id){
        Map<String, Object> res = new HashMap<>();
        try{
            ReviewRepository reviewRepository = new ReviewRepository();
            ReviewCommand deletedReview = new DeleteReviewCommand(id, reviewRepository);
            reviewService.executeCommand(deletedReview);

            res.put("code", HttpStatus.OK.value());
            res.put("message", "Review Deleted Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch (Exception e){
            res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.put("error", e.getMessage());
            res.put("message", "Something Wrong With Server");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping
    public ResponseEntity<?> editReview(@RequestBody Review review){
        Map<String, Object> res = new HashMap<>();
        try{
            ReviewRepository reviewRepository = new ReviewRepository();
            ReviewCommand editedReview = new EditReviewCommand(reviewRepository, review);
            reviewService.executeCommand(editedReview);

            res.put("listing", editedReview);
            res.put("message", "Listing ID " + review.getReviewId() +" updated Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }catch (Exception e){
            res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.put("error", e.getMessage());
            res.put("message", "Something Wrong With Server");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping("/findAllReviews")
    public ResponseEntity<?> findAllReviews(){
        try {
            List<Review> reviews = reviewService.findAll();
            return ResponseEntity.ok(reviews);
        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", e.getMessage());
            response.put("message", "Something Wrong With Server");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
