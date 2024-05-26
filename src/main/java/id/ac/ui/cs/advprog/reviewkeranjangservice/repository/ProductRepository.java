package id.ac.ui.cs.advprog.reviewkeranjangservice.repository;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
}
