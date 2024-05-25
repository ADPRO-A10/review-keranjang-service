package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItem;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.util.Map;
import java.util.UUID;

public class StockHandler extends BaseHandler{
    private final RestTemplate restTemplate;

    public StockHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void handleRequest(Iterable<CartItem> cartItems) {
        for (CartItem item : cartItems) {
            int stockKeranjang = item.getJumlah();
            UUID idItem = item.getId().getItemId();
            int stockPenjual = getStock(idItem.toString());

            if (stockKeranjang > stockPenjual) {
                this.setStatus("FAILED");
                throw new IllegalArgumentException();
            }
        }

        this.setStatus("PASSED");
        if (this.getNextHandler() != null) {
            this.getNextHandler().handleRequest(cartItems);
        }

    }

    public int getStock(String id) {
        String produkApi = "34.143.169.251";
        String apiUrl = "https://" + produkApi + "/api/products/" + id;

        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        JSONObject jsonObject = new JSONObject(jsonResponse);

        return jsonObject.getInt("stokTersedia");
    }
}
