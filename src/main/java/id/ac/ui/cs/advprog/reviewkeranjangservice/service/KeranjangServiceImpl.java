package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItem;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.CartItemRepository;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.KeranjangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class KeranjangServiceImpl implements KeranjangService{

    @Autowired
    private KeranjangRepository keranjangRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    RestTemplate restTemplate = new RestTemplate();
    BalanceHandler balanceHandler = new BalanceHandler(restTemplate);
    StockHandler stockHandler = new StockHandler(restTemplate);
    @Override
    public Keranjang createKeranjang(Keranjang keranjang) {
        return keranjangRepository.save(keranjang);
    }

    @Override
    public Keranjang findById(UUID keranjangId) {
        Optional<Keranjang> result = keranjangRepository.findById(keranjangId);

        return result.orElse(null);
    }

    @Override
    public void checkOut(UUID keranjangId) {
        stockHandler.setNext(balanceHandler);

        Iterable<CartItem> cartItems = cartItemRepository.findAllById_CartId(keranjangId);
        stockHandler.handleRequest(cartItems);
    }

    @Override
    public Iterable<CartItem> findAllCartItems(UUID keranjangId) {
        return cartItemRepository.findAllById_CartId(keranjangId);
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(UUID keranjangId, UUID itemId) {
        CartItem cartItem = cartItemRepository.findById_CartIdAndId_ItemId(keranjangId, itemId);

        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
        }
    }
}
