package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItem;

import java.util.Map;

public interface Handler {
    void setNext(Handler handler);
    void handleRequest(Iterable<CartItem> cartItems);
}
