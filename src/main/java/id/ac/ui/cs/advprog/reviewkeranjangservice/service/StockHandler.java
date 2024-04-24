package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.util.Map;

public class StockHandler extends BaseHandler{
    private final RestTemplate restTemplate;

    public StockHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void handleRequest(Map<String, Object> request) {
        for (String key : request.keySet()) {
            int stockKeranjang = (int) request.get(key);
            int stockPenjual = getStock(key);

            if (stockKeranjang > stockPenjual) {
                this.setStatus("FAILED");
                throw new IllegalArgumentException();
            }
        }

        this.setStatus("PASSED");
        if (this.getNextHandler() != null) {
            this.getNextHandler().handleRequest(request);
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
