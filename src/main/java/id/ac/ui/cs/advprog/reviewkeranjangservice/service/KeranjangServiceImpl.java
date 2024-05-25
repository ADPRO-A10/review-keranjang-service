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
        Optional<Keranjang> result = keranjangRepository.findById(keranjang.getId());

        if (result.isEmpty()) {
            keranjangRepository.save(keranjang);
            return keranjang;
        }

        return null;
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
}
