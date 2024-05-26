package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.command.ReviewCommand;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Product;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Review;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<Product> getAllProducts();
}
