package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import java.util.Map;

public class BalanceHandler extends BaseHandler {

    @Override
    public void handleRequest(Map<String, Object> request) {
        int saldo = (int) request.get("saldo");
        int totalBiaya = (int) request.get("totalBiaya");

        if (saldo >= totalBiaya) {
            this.setStatus("PASSED");

            if (this.getNextHandler() != null) {
                this.getNextHandler().handleRequest(request);
            }
        } else {
            this.setStatus("FAILED");
            throw new IllegalArgumentException();
        }
    }
}
