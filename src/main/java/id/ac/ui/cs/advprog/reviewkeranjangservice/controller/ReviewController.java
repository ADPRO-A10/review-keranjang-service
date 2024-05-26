package id.ac.ui.cs.advprog.reviewkeranjangservice.controller;

import id.ac.ui.cs.advprog.reviewkeranjangservice.command.CreateReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.DeleteReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.EditReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.command.ReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.ReviewRepository;
import id.ac.ui.cs.advprog.reviewkeranjangservice.service.ProductService;
import id.ac.ui.cs.advprog.reviewkeranjangservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ProductService productService;

    @Autowired
    private ReviewRepository reviewRepository;

//    @GetMapping("/createReview")
//    public String CreateReviewPage(Model model) {
//        model.addAttribute("review", new Review());
//        model.addAttribute("products", productService.getAllProducts());
//        return "createReview";
//    }
//
//    @PostMapping("/createReview")
//    public String createReviewPost(@ModelAttribute Review review, Model model) {
//        try {
//            ReviewCommand createReviewCommand = new CreateReviewCommand(review, reviewRepository);
//            Optional<Review> createdReview = reviewService.executeCommand(createReviewCommand);
//
//            model.addAttribute("review", new Review()); // Reset the form
//            return "redirect:list"; // Redirect to the form page after successful submission
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Something went wrong: " + e.getMessage());
//            return "createReview"; // Return to the form page with error message
//        }
//    }

    @PostMapping("/createReview")
    public ResponseEntity<?> createReview(@RequestBody Review review){
        Map<String, Object> res = new HashMap<>();
        try{
            ReviewCommand createReviewCommand = new CreateReviewCommand(review, reviewRepository);
            Optional<Review> createdReview = reviewService.executeCommand(createReviewCommand);

            res.put("review", createdReview);
            res.put("message", "Review Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }catch (Exception e){
            res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.put("error", e.getMessage());
            res.put("message", "Something Wrong With Server");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

//    @GetMapping("/delete/{reviewId}")
//    public String deleteReview(@PathVariable("reviewId") String id, Model model) {
//        try {
//            ReviewCommand deletedReview = new DeleteReviewCommand(id, reviewRepository);
//            reviewService.executeCommand(deletedReview);
//
//            return "redirect:/review/list"; // Redirect to the review list page
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Something went wrong: " + e.getMessage());
//            return "reviewList"; // Return to the list page with error message
//        }
//    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") String id){
        Map<String, Object> res = new HashMap<>();
        try{
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

//    @GetMapping("/editReview/{reviewId}")
//    public String editReviewPage(@PathVariable("reviewId") String id, Model model) {
//        Optional<Review> review = reviewRepository.findById(id);
//        if (review.isPresent()) {
//            model.addAttribute("review", review.get());
//            model.addAttribute("products", productService.getAllProducts());
//            return "editReview";
//        } else {
//            model.addAttribute("errorMessage", "Review not found");
//            return "redirect:/review/list";
//        }
//    }
//
//    @PostMapping("/editReview/{reviewId}")
//    public String editReview(@PathVariable("reviewId") String id, @ModelAttribute Review review, Model model) {
//        try {
//            ReviewCommand editReviewCommand = new EditReviewCommand(reviewRepository, review);
//            Optional<Review> editedReview = reviewService.executeCommand(editReviewCommand);
//
//            if (editedReview.isPresent()) {
//                return "redirect:/review/list"; // Redirect to the review list page after successful edit
//            } else {
//                model.addAttribute("errorMessage", "Review not found");
//                return "reviewEdit"; // Return to the edit page with error message
//            }
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Something went wrong: " + e.getMessage());
//            return "reviewEdit"; // Return to the edit page with error message
//        }
//    }

    @PostMapping("/editReview")
    public ResponseEntity<?> editReview(@RequestBody Review review){
        Map<String, Object> res = new HashMap<>();
        try{
            ReviewCommand editReviewCommand = new EditReviewCommand(reviewRepository, review);
            Optional<Review> editedReview = reviewService.executeCommand(editReviewCommand);

            if (editedReview.isPresent()) {
                res.put("listing", editedReview.get());
                res.put("message", "Review ID " + review.getReviewId() +" updated Successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(res);
            } else {
                res.put("code", HttpStatus.NOT_FOUND.value());
                res.put("message", "Review not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
        }catch (Exception e){
            res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.put("error", e.getMessage());
            res.put("message", "Something Wrong With Server");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

//    @GetMapping("/list")
//    public String listReviews(Model model) {
//        try {
//            List<Review> reviews = reviewService.findAll();
//            model.addAttribute("reviews", reviews);
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Something went wrong: " + e.getMessage());
//        }
//        return "reviewList";
//    }

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
