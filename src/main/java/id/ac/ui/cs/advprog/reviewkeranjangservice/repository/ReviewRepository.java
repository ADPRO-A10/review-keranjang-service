package id.ac.ui.cs.advprog.reviewkeranjangservice.repository;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, String> {
}
