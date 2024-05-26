package id.ac.ui.cs.advprog.reviewkeranjangservice.repository;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItem;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItemKey;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CartItemRepository extends CrudRepository<CartItem, CartItemKey> {
    Iterable<CartItem> findAllById_CartId(UUID cartId);
    CartItem findById_CartIdAndId_ItemId(UUID cartId, UUID itemId);
}
