package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import com.github.javafaker.Faker;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItem;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItemKey;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.CartItemRepository;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.KeranjangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class DataSeedService {
    @Autowired
    private KeranjangRepository keranjangRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    private static final int NUMBER_OF_KERANJANG = 30;
    private static final int NUMBER_OF_ITEM = 500;

    public void seedKeranjang() {
        for (int i = 0; i < NUMBER_OF_KERANJANG; i++) {
            Keranjang keranjang = new Keranjang();
            keranjangRepository.save(keranjang);
        }
    }

    public void seedCartItem() {
        Faker faker = new Faker(new Locale("in-ID"));

        Iterable<Keranjang> keranjangs = keranjangRepository.findAll();
        List<Keranjang> keranjangList = new ArrayList<>();
        keranjangs.forEach(keranjangList::add);

        for (int i = 0; i < NUMBER_OF_ITEM; i++) {
            CartItem cartItem = new CartItem();
            CartItemKey cartItemKey = new CartItemKey();

            cartItemKey.setCartId(keranjangList.get(faker.number().numberBetween(0, keranjangList.size())).getId());
            cartItemKey.setItemId(UUID.randomUUID());

            cartItem.setId(cartItemKey);
            cartItem.setJumlah(faker.number().numberBetween(1, 10));
            cartItem.setHarga(faker.number().numberBetween(1000, 100000));
            cartItem.setNama(faker.commerce().productName());

            cartItemRepository.save(cartItem);
        }
    }
}
