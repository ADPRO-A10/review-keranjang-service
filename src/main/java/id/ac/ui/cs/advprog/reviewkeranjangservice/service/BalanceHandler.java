package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItem;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class BalanceHandler extends BaseHandler {
    private final RestTemplate restTemplate;

    public BalanceHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void handleRequest(Iterable<CartItem> cartItems) {
        int saldo = getSaldo(cartItems.iterator().next().getId().getCartId().toString());
        int totalBiaya = 0;

        for (CartItem cartItem : cartItems) {
            totalBiaya += cartItem.getJumlah() * cartItem.getHarga();
        }

        if (saldo >= totalBiaya) {
            this.setStatus("PASSED");

            if (this.getNextHandler() != null) {
                this.getNextHandler().handleRequest(cartItems);
            }
        } else {
            this.setStatus("FAILED");
            throw new IllegalArgumentException();
        }
    }

    public int getSaldo(String id) {
        String authApi = "34.143.204.34";
        String apiUrl = "https://" + authApi + "/api/saldo/" + id;

        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        JSONObject jsonObject = new JSONObject(jsonResponse);

        return jsonObject.getInt("saldo");
    }
}
