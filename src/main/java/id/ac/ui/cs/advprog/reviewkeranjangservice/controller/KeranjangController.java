package id.ac.ui.cs.advprog.reviewkeranjangservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItem;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItemKey;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;
import id.ac.ui.cs.advprog.reviewkeranjangservice.service.KeranjangService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class KeranjangController {
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    KeranjangService keranjangService;

    @GetMapping("/keranjang")
    public String renderKeranjangPage() {
        return "Hello World Keranjang!!";
    }

    @PostMapping("api/keranjang")
    public ResponseEntity<String> createKeranjang() throws JsonProcessingException {
        Keranjang keranjang = new Keranjang();

        Keranjang result = keranjangService.createKeranjang(keranjang);
        JSONObject response = new JSONObject();

        if (result == null) {
            response.put("message", "Keranjang gagal dibuat");
            return ResponseEntity.badRequest().body(response.toString());
        }

        String data = objectMapper.writeValueAsString(keranjang);
        response.put("message", "Keranjang berhasil dibuat");
        response.put("data", data);
        return ResponseEntity.ok(response.toString());
    }

    @GetMapping("api/keranjang/{keranjangId}")
    public ResponseEntity<Iterable<CartItem>> getKeranjang(@PathVariable String keranjangId) {
        Iterable<CartItem> result = keranjangService.findAllCartItems(UUID.fromString(keranjangId));
        return ResponseEntity.ok(result);
    }

    @PostMapping("api/itemcart/")
    public ResponseEntity<CartItem> addCartItemToCart(@RequestBody Map<String, String> request) {
        String keranjangId = request.get("keranjangId");
        String itemId = request.get("itemId");
        String jumlah = request.get("jumlah");
        String harga = request.get("harga");
        String nama = request.get("nama");

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
