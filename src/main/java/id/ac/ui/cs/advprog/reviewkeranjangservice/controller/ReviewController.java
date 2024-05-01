package id.ac.ui.cs.advprog.reviewkeranjangservice.controller;

import id.ac.ui.cs.advprog.reviewkeranjangservice.command.CreateReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.DeleteReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.EditReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.ReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private CreateReviewCommand createReviewCommand;

    @Autowired
    private DeleteReviewCommand deleteReviewCommand;

    @Autowired
    private EditReviewCommand editReviewCommand;

    @GetMapping
    public String renderReviewPage() {
        return "Hello World Review!!";
    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody Review review){
        Map<String, Object> res = new HashMap<>();
        try{
            ReviewRepository reviewRepository = new ReviewRepository();
            CreateReviewCommand createReview = new CreateReviewCommand(review, reviewRepository);
            createReview.execute();

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
            DeleteReviewCommand deletedReview = new DeleteReviewCommand(id, reviewRepository);
            deletedReview.execute();
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
    public ResponseEntity<?> updateReview(@RequestBody Review review){
        Map<String, Object> res = new HashMap<>();
        try{
            ReviewRepository reviewRepository = new ReviewRepository();
            EditReviewCommand editedReview = new EditReviewCommand(reviewRepository, review);
            editedReview.execute();

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

}
