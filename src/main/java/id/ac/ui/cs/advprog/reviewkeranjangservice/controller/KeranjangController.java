package id.ac.ui.cs.advprog.reviewkeranjangservice.controller;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItem;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItemKey;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;
import id.ac.ui.cs.advprog.reviewkeranjangservice.service.KeranjangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class KeranjangController {

    @Autowired
    KeranjangService keranjangService;

    @PostMapping("api/keranjang")
    public ResponseEntity<Keranjang> createKeranjang() {
        Keranjang keranjang = new Keranjang();

        Keranjang result = keranjangService.createKeranjang(keranjang);
        return ResponseEntity.ok(result);
    }

    @GetMapping("api/keranjang/{keranjangId}")
    public ResponseEntity<Iterable<CartItem>> getKeranjang(@PathVariable String keranjangId) {
        Iterable<CartItem> result = keranjangService.findAllCartItems(UUID.fromString(keranjangId));
        return ResponseEntity.ok(result);
    }

    @PostMapping("api/itemcart")
    public ResponseEntity<CartItem> addCartItemToCart(@RequestParam String keranjangId, @RequestParam String itemId, @RequestParam String jumlah, @RequestParam String harga, @RequestParam String nama) {

        CartItemKey cartItemKey = new CartItemKey();
        cartItemKey.setCartId(UUID.fromString(keranjangId));
        cartItemKey.setItemId(UUID.fromString(itemId));

        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemKey);
        cartItem.setJumlah(Integer.parseInt(jumlah));
        cartItem.setHarga(Integer.parseInt(harga));
        cartItem.setNama(nama);

        return ResponseEntity.ok(keranjangService.createCartItem(cartItem));
    }

    @PostMapping("api/checkout/{keranjangId}")
    public ResponseEntity<String> checkout(@PathVariable String keranjangId) {
        keranjangService.checkOut(UUID.fromString(keranjangId));
        return ResponseEntity.ok("Checkout berhasil");
    }

    @DeleteMapping("api/itemcart/{keranjangId}/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable String keranjangId, @PathVariable String itemId) {
        keranjangService.deleteCartItem(UUID.fromString(keranjangId), UUID.fromString(itemId));
        return ResponseEntity.ok("Item berhasil dihapus");
    }
}
